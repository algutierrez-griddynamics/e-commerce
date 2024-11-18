package org.ecommerce.services.jpa.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.*;
import org.ecommerce.mappers.BuildOrderFromDTORequest;
import org.ecommerce.mappers.OrderDTOMapper;
import org.ecommerce.models.*;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.*;
import org.ecommerce.repositories.jpa.OrderJpaRepository;
import org.ecommerce.services.OperationsService;
import org.ecommerce.services.jpa.OrderJpaService;
import org.ecommerce.services.jpa.PaymentDetailsI;
import org.ecommerce.services.jpa.ShippingInformationI;
import org.ecommerce.services.jpa.StockServiceI;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderJpaServiceImpl implements OrderJpaService <OrderRequestDTO, Long> {

    private final OrderJpaRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;
    private final BuildOrderFromDTORequest buildOrderFromDTORequest;
    private final EntityManager entityManager;

    // Micro-services
    private final OperationsService<Product, Long> productService;
    private final ShippingInformationI shippingInformationService;
    private final PaymentDetailsI paymentDetailsService;
    private final StockServiceI stockService;
    private final BillingInformationI billingInformationService;

    public OrderJpaServiceImpl(OrderJpaRepository orderRepository, OrderDTOMapper orderDTOMapper,
                               BuildOrderFromDTORequest buildOrderFromDTORequest, EntityManager entityManager,
                               OperationsService<Product, Long> productService, ShippingInformationI shippingInformationService,
                               PaymentDetailsI paymentDetailsService, StockServiceI stockService, BillingInformationI billingInformationService) {
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
        this.buildOrderFromDTORequest = buildOrderFromDTORequest;
        this.entityManager = entityManager;


        this.productService = productService;
        this.shippingInformationService = shippingInformationService;
        this.paymentDetailsService = paymentDetailsService;
        this.stockService = stockService;
        this.billingInformationService = billingInformationService;
    }

    @Override
    @Transactional
    public CreateOrderResponse create(CreateRequest<OrderRequestDTO> entity) {
        OrderRequestDTO order = entity.getData();
        Map<Long, Long> quantityOfEachProductForId = getTotalQuantityOfEachProduct(order.productsIds());


        if (!validateBillingInformation(order.fk_billing_information_id())) {
            throw new BillingInformationException(Error.INVALID_BILLING_INFORMATION.getDescription());
        }

        if (!validatePaymentDetails(order.fk_payment_details_id())) {
            throw new PaymentDetailsException(Error.PAYMENT_DECLINED.getDescription());
        }

        if (!checkStock(order.productsIds(), quantityOfEachProductForId)) {
            throw new OutOfStockException(Error.OUT_OF_STOCK.getDescription());
        }

        if (!validateShippingInformation(order.fk_shipping_information_id())) {
            throw new ShippingInformationException(Error.INVALID_SHIPPING_INFORMATION.getDescription());
        }

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
            throw new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription());
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
            throw new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription());
        }
    }

    @Override
    public GetAllOrdersResponse findAll() {
        return new GetAllOrdersResponse(
                orderRepository.findAll().stream()
                        .map(order -> {
                            orderDTOMapper.apply(order);
                            return orderDTOMapper.apply(order);
                        }).collect(Collectors.toList())
        );
    }

    @Override
    public GetOrderResponse findById(Long id) {
        return new GetOrderResponse(
                orderRepository.findById(id).map(orderDTOMapper)
                        .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()))
        );
    }

    // TODO: Should the following methods be performed for a client to distinct microservices?

    private BigDecimal getTotalOfProducts(List<Long> productsIds) {
        return productsIds.stream()
                .map(productService::findById)
                .map(product -> product.getPrice().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalOfShipment(Long shipmentId) {
        return shippingInformationService.findById(shipmentId).getShippingCost().getAmount();
    }

    private boolean validatePaymentDetails(Long paymentDetailsId) {
        PaymentDetails paymentDetails = paymentDetailsService.findById(paymentDetailsId);

        if (Objects.nonNull(paymentDetails)) {
            return paymentDetailsService.validatePaymentDetails(paymentDetails);
        }

        return false;
    }

    private boolean validateBillingInformation(Long billingInformationId) {
        BillingInformation billingInformation = billingInformationService.findById(billingInformationId);

        if (Objects.nonNull(billingInformation)) {
            return billingInformationService.validateBillingInformation();
        }

        return false;
    }

    private boolean validateShippingInformation (Long shippingInformationId) {
        ShippingInformation shippingInformation = shippingInformationService.findById(shippingInformationId);

        if (Objects.nonNull(shippingInformation)) {
            return shippingInformationService.validateShippingInformation(shippingInformation);
        }

        return false;
    }


    private boolean checkStock(List<Long> productsIds, Map<Long, Long> quantityOfEachProductForId) {
        for (Long productId : productsIds) {
            Product product = productService.findById(productId);
            if (Objects.nonNull(product)) {
                int stockOfProduct = stockService.getStockOfProduct(product);
                if (stockOfProduct < quantityOfEachProductForId.get(productId))
                    return false;
            }
        }

        return true;
    }

    private void reduceStock(List<Long> productsIds, Map<Long, Long> quantityOfEachProductForId) {
        for(Long productId : productsIds) {
            Product product = productService.findById(productId);
            stockService.setStockOfProduct(product,
                    (int) (stockService.getStockOfProduct(product) - quantityOfEachProductForId.get(productId)));
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
