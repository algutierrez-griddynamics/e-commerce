package org.ecommerce.services.jpa.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.*;
import org.ecommerce.feign.ShippingInformationInterface;
import org.ecommerce.mappers.BuildOrderFromDTORequest;
import org.ecommerce.mappers.OrderDTOMapper;
import org.ecommerce.models.*;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.*;
import org.ecommerce.repositories.jpa.OrderJpaRepository;
import org.ecommerce.services.ProductService;
import org.ecommerce.services.jpa.OrderJpaService;
import org.ecommerce.services.jpa.ShippingInformationI;
import org.ecommerce.services.jpa.StockServiceI;
import org.ecommerce.services.jpa.validators.OrderValidatorService;
import org.ecommerce.util.money.operations.UsdConverter;
import org.ecommerce.util.database.specifications.OrderSpecifications;
import org.ecommerce.util.database.specifications.SpecificationParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderJpaServiceImpl implements OrderJpaService <OrderRequestDTO, Long> {

    private final String DESTINATION_CURRENCY_CODE = "USD";

    private final OrderJpaRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;
    private final BuildOrderFromDTORequest buildOrderFromDTORequest;
    private final EntityManager entityManager;

    // Embedded services
    private final ProductService productService;
    private final StockServiceI stockService;
    private final UsdConverter usdConverter;

    // Micro-services
    private final ShippingInformationInterface shippingInformationInterface;

    // Validators
    private final OrderValidatorService orderValidatorService;
    private final Order order;

    public OrderJpaServiceImpl(OrderJpaRepository orderRepository, OrderDTOMapper orderDTOMapper,
                               BuildOrderFromDTORequest buildOrderFromDTORequest, EntityManager entityManager,
                               ProductService productService, StockServiceI stockService,
                               ShippingInformationInterface shippingInformationInterface,
                               OrderValidatorService orderValidatorService, Order order, UsdConverter usdConverter) {
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
        this.buildOrderFromDTORequest = buildOrderFromDTORequest;
        this.entityManager = entityManager;

        this.productService = productService;
        this.stockService = stockService;

        this.shippingInformationInterface = shippingInformationInterface;

        this.orderValidatorService = orderValidatorService;
        this.order = order;

        this.usdConverter = usdConverter;
    }

    @Override
    @Transactional
    public CreateOrderResponse create(CreateRequest<OrderRequestDTO> entity) {
        OrderRequestDTO order = entity.getData();
        Map<Long, Long> quantityOfEachProductForId = getTotalQuantityOfEachProduct(order.productsIds());

        orderValidatorService.validateOrder(order, quantityOfEachProductForId);

        Order newOrder = buildOrderFromDTORequest.apply(order);

        BigDecimal shippingFee = getTotalOfShipment(order.fk_shipping_information_id());
        BigDecimal totalFromProducts = getTotalOfProducts(order.productsIds());
        newOrder.setTotalUsd(shippingFee.add(totalFromProducts));

        reduceStock(order.productsIds(), quantityOfEachProductForId);
        Order savedOrder = orderRepository.save(newOrder);
        entityManager.clear();

        return new CreateOrderResponse(findById(savedOrder.getId()).getOrderDTO());
    }



    @Override
    @Transactional
    public UpdateOrderResponse update(UpdateRequest<OrderRequestDTO, Long> entity) {
        Long id = entity.getId();
        OrderDTO existingOrder = findById(id).getOrderDTO();

        if (existingOrder == null) {
            throw new EntityNotFound(Order.class.getSimpleName(), Error.ENTITY_NOT_FOUND.getDescription());
        }

        Order updatedOrder = buildOrderFromDTORequest.apply(entity.getData());
        updatedOrder.setId(id);

        orderRepository.saveAndFlush(updatedOrder);
        entityManager.clear();

        return new UpdateOrderResponse(findById(updatedOrder.getId()).getOrderDTO());
    }

    @Override
    public void delete(Long id) {
        if (Objects.nonNull(findById(id))) {
            orderRepository.deleteById(id);
        } else {
            throw new EntityNotFound(Order.class.getSimpleName(), Error.ENTITY_NOT_FOUND.getDescription());
        }
    }

    @Override
    public GetAllOrdersResponse findAll(@NotNull SpecificationParameters specificationParameters, Pageable pageable) {
        Page<Order> ordersPage = orderRepository
                .findAll(
                        OrderSpecifications.allSpecifications(specificationParameters)
                        , pageable
                );

        return new GetAllOrdersResponse(
                ordersPage.getContent().stream()
                        .map(orderDTOMapper).collect(Collectors.toList())
                , ordersPage.getNumber()
                , ordersPage.getSize()
                , ordersPage.getTotalElements()
                , ordersPage.getTotalPages());
    }

    @Override
    public GetOrderResponse findById(Long id) {
        return new GetOrderResponse(
                orderRepository.findById(id).map(orderDTOMapper)
                        .orElseThrow(() -> new EntityNotFound(Order.class.getSimpleName(), Error.ENTITY_NOT_FOUND.getDescription()))
        );
    }

    private BigDecimal getTotalOfProducts(List<Long> productsIds) {
        return productsIds.stream()
                .map(productService::findById)
                .map(product -> {
                    try {
                        return usdConverter.convertAmountFromTo(product.getPrice().getCurrencyCode().toString(), DESTINATION_CURRENCY_CODE,
                                product.getPrice().getAmount());
                    } catch (JsonProcessingException e) {
                        throw new JsonParserException(e.getMessage(), e);
                    }
                }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalOfShipment(Long shipmentId) {
        ShippingInformation shippingInformation = shippingInformationInterface.getShippingInformation(shipmentId);
        String from = shippingInformation.getShippingCost().getCurrencyCode().toString();
        BigDecimal amount  = shippingInformation.getShippingCost().getAmount();

        try {
            return usdConverter.convertAmountFromTo(from, DESTINATION_CURRENCY_CODE, amount);
        } catch (JsonProcessingException e) {
            throw new JsonParserException(e.getMessage(), e);
        }
    }

    private void reduceStock(List<Long> productsIds, Map<Long, Long> quantityOfEachProductForId) {
        for(Long productId : productsIds) {
            Product product = productService.findById(productId);
            stockService.setStockOfProduct(product,
                    (int) (stockService.getStockOfProduct(product) - quantityOfEachProductForId.getOrDefault(productId, 0L)));
        }
    }

    private Map<Long, Long> getTotalQuantityOfEachProduct(List<Long> productsIds) {
        return productsIds.stream()
                .map(productService::findById)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Identity::getId
                        , Collectors.counting()));
    }



}
