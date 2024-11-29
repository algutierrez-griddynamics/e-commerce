package org.ecommerce.services.jpa.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import org.ecommerce.config.ApplicationServicesConfig;
import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.feign.ShippingInformationInterface;
import org.ecommerce.mappers.BuildOrderFromDTORequest;
import org.ecommerce.mappers.OrderDTOMapper;
import org.ecommerce.models.Order;
import org.ecommerce.models.Price;
import org.ecommerce.models.Product;
import org.ecommerce.models.ShippingInformation;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.CreateOrderResponse;
import org.ecommerce.models.services.responses.GetAllOrdersResponse;
import org.ecommerce.models.services.responses.GetOrderResponse;
import org.ecommerce.models.services.responses.UpdateOrderResponse;
import org.ecommerce.repositories.jpa.OrderJpaRepository;
import org.ecommerce.services.ProductService;
import org.ecommerce.services.jpa.validators.OrderValidatorService;
import org.ecommerce.util.database.specifications.SpecificationParameters;
import org.ecommerce.util.money.operations.ApiCurrencyConverterService;
import org.ecommerce.util.money.operations.UsdConverter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Import(ApplicationServicesConfig.class)
class OrderJpaServiceImplTest {

    @Mock
    private OrderDTOMapper orderDTOMapper;

    @Mock
    private ProductService productRepository;

    @Mock
    private ShippingInformationInterface shippingInformationService;

    @Mock
    private UsdConverter usdConverter;

    @Mock
    private ApiCurrencyConverterService apiCurrencyConverterService;

    @InjectMocks
    private OrderJpaServiceImpl orderJpaService;

    @Mock
    private Price price1;

    @Mock
    private Price price2;

    @Mock
    private Product product1;

    @Mock
    private Product product2;

    @Mock
    private ShippingInformation shippingInformation;

    @Mock
    private CreateRequest<OrderRequestDTO> createRequest;

    @Mock
    private UpdateRequest<OrderRequestDTO, Long> updateRequest;

    @Mock
    private OrderRequestDTO orderRequestDTO;

    @Mock
    private Order order;

    @Mock
    private OrderValidatorService orderValidatorService;

    @Mock
    private StockJpaServiceImpl stockService;

    @Mock
    private BuildOrderFromDTORequest buildOrderFromDTORequest;

    @Mock
    private OrderJpaRepository orderJpaRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private OrderDTO orderDTO;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Order> page;

    @Mock
    private SpecificationParameters specificationParameters;

    Currency gbpCurrency = Currency.getInstance("GBP");
    Currency mxnCurrency = Currency.getInstance("MXN");
    final String DESTINATION_CURRENCY_CODE = "USD";

    @DisplayName("Assess create service method implementation")
    @Test
    void create() throws JsonProcessingException {
        List<Long> productIds = Arrays.asList(2001L, 2002L, 2001L, 2001L, 2001L, 2001L, 2002L);
        Long orderId = 1L;

        when(productRepository.findById(2001L)).thenReturn(product1);
        when(productRepository.findById(2002L)).thenReturn(product2);

        when(price1.getAmount()).thenReturn(BigDecimal.valueOf(100));
        when(price2.getAmount()).thenReturn(BigDecimal.valueOf(200));

        when(product1.getPrice()).thenReturn(price1);
        when(product2.getPrice()).thenReturn(price2);

        when(order.getId()).thenReturn(orderId);
        when(orderRequestDTO.productsIds()).thenReturn(productIds);

        when(createRequest.getData()).thenReturn(orderRequestDTO);
        doNothing().when(orderValidatorService).validateOrder(any(OrderRequestDTO.class), anyMap());

        when(shippingInformation.getShippingCost()).thenReturn(price1);
        when(price1.getAmount()).thenReturn(BigDecimal.valueOf(100));

        when(buildOrderFromDTORequest.apply(any(OrderRequestDTO.class))).thenReturn(order);
        when(orderDTOMapper.apply(any(Order.class))).thenReturn(orderDTO);

        when(shippingInformationService.getShippingInformation(anyLong())).thenReturn(shippingInformation);

        when(orderJpaRepository.save(any(Order.class))).thenReturn(order);
        when(orderJpaRepository.findById(orderId)).thenReturn(Optional.of(order));

        doNothing().when(entityManager).clear();

        when(stockService.getStockOfProduct(any(Product.class))).thenReturn(1);
        doNothing().when(stockService).setStockOfProduct(any(Product.class), anyInt());

        when(price1.getCurrencyCode()).thenReturn(gbpCurrency);
        when(price2.getCurrencyCode()).thenReturn(mxnCurrency);

        when(usdConverter.convertAmountFromTo(anyString(), anyString(), any(BigDecimal.class))).thenReturn(new BigDecimal(1));

        AtomicReference<CreateOrderResponse> createOrderResponse = new AtomicReference<>();

        assertDoesNotThrow(() -> createOrderResponse.set(orderJpaService.create(createRequest)));
        assertNotNull(createOrderResponse);
    }

    @DisplayName("Assess Update service method implementation when updating successfully")
    @Test
    void updateOrderSuccessfully() {
        Long orderId = 1L;
        when(updateRequest.getData()).thenReturn(orderRequestDTO);
        when(updateRequest.getId()).thenReturn(orderId);

        when(orderJpaRepository.findById(anyLong())).thenReturn(Optional.of(order));
        when(buildOrderFromDTORequest.apply(any(OrderRequestDTO.class))).thenReturn(order);

        when(orderDTOMapper.apply(any(Order.class))).thenReturn(orderDTO);
        when(orderJpaRepository.saveAndFlush(any(Order.class))).thenReturn(order);
        doNothing().when(entityManager).clear();

        AtomicReference<UpdateOrderResponse> updateOrderResponse = new AtomicReference<>();

        assertDoesNotThrow(() -> updateOrderResponse.set(orderJpaService.update(updateRequest)));
        assertNotNull(updateOrderResponse);
    }

    @DisplayName("Assess Update service method implementation throws EntityNotFoundException when there is not register of the entity")
    @Test
    void updateOrderUnsuccessfully() {
        when(orderJpaRepository.findById(anyLong())).thenThrow(EntityNotFound.class);

        assertThrows(EntityNotFound.class,
                () -> orderJpaService.update(updateRequest));
    }

    @DisplayName("Assess delete service method implementation does not throws anything")
    @Test
    void deleteDoesNotThrow() {
        Long orderId = 1L;
        when(orderJpaRepository.findById(orderId)).thenReturn(Optional.of(order));
        doNothing().when(orderJpaRepository).deleteById(orderId);
        when(orderDTOMapper.apply(any(Order.class))).thenReturn(orderDTO);

        assertDoesNotThrow(() -> orderJpaService.delete(orderId));
    }

    @DisplayName("Assess delete service method implementation throws EntityNotFoundException")
    @Test
    void deleteThrowsEntityNotFoundException() {
        Long orderId = 1L;
        when(orderJpaRepository.findById(orderId)).thenThrow(EntityNotFound.class);

        assertThrows(EntityNotFound.class,
                () -> orderJpaService.delete(orderId));
    }

    @DisplayName("Assess findAll service method implementation")
    @Test
    void findAll() {
        when(page.getContent()).thenReturn(List.of(order));
        when(orderJpaRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(orderDTOMapper.apply(any(Order.class))).thenReturn(orderDTO);

        GetAllOrdersResponse orders = orderJpaService.findAll(specificationParameters, pageable);
        List<OrderDTO> orderDTOs = orders.getOrders();

        assertAll(
                () -> {
                    assertNotNull(orderDTOs);
                    assertFalse(orderDTOs.isEmpty());
                    assertEquals(1, orderDTOs.size());
                }
        );
    }

    @DisplayName("Assess the findAll metadata")
    @Test
    void findAllAssesPagination() {
        final List<Order> mockedList = List.of(order, order, order);
        when(page.getContent()).thenReturn(mockedList);

        when(orderDTOMapper.apply(any(Order.class))).thenReturn(orderDTO);
        when(orderJpaRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(page);

        GetAllOrdersResponse ordersPage = orderJpaService.findAll(specificationParameters, pageable);
        List<OrderDTO> orderDTOs = ordersPage.getOrders();
        assertAll(
                () -> {
                    assertNotNull(orderDTOs);
                    assertFalse(orderDTOs.isEmpty());
                    assertEquals(mockedList.size(), orderDTOs.size());
                    assertEquals(0, ordersPage.getCurrentPage());
                    assertEquals(0, ordersPage.getPageSize());
                    assertEquals(0, ordersPage.getTotalPages());
                }
        );
    }

    @DisplayName("Check findAll parameter pageable works as expected")
    @Test
    void findAllAssesPageableParameter() {
        final List<Order> mockedList = List.of(order, order, order);
        final int pageSize = 2;
        final int pageNumber = 1;
        final int totalPages = mockedList.size() / pageSize;

        when(pageable.getPageNumber()).thenReturn(pageNumber);
        when(pageable.getPageSize()).thenReturn(pageSize);
        when(pageable.getSort()).thenReturn(Sort.unsorted());

        when(orderDTOMapper.apply(any(Order.class))).thenReturn(orderDTO);

        when(orderJpaRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        when(page.getContent()).thenReturn(mockedList);
        when(page.getTotalElements()).thenReturn((long) mockedList.size());
        when(page.getNumber()).thenReturn(pageNumber);
        when(page.getSize()).thenReturn(pageSize);
        when(page.getTotalPages()).thenReturn(totalPages);

        GetAllOrdersResponse ordersPage = orderJpaService.findAll(specificationParameters, pageable);
        List<OrderDTO> orderDTOs = ordersPage.getOrders();


        assertAll(
                () -> {
                    assertNotNull(orderDTOs);
                    assertFalse(orderDTOs.isEmpty());
                    assertEquals(mockedList.size(), ordersPage.getTotalElements());
                    assertEquals(pageNumber, ordersPage.getCurrentPage());
                    assertEquals(pageSize, ordersPage.getPageSize());
                    assertEquals(totalPages, ordersPage.getTotalPages());
                }
        );

    }

    @DisplayName("Assess that an entity is returned when it exists in the database")
    @Test
    void findById_foundedEntity() {
        Long orderId = 1L;
        when(order.getId()).thenReturn(orderId);

        when(orderJpaRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(orderDTOMapper.apply(any(Order.class))).thenReturn(orderDTO);

        AtomicReference<GetOrderResponse> orderDTOAtomicReference = new AtomicReference<>();

        assertDoesNotThrow(() -> orderDTOAtomicReference.set(orderJpaService.findById(orderId)));
        assertNotNull(orderDTOAtomicReference);
    }

    @DisplayName("Assess that an NotFoundException is thrown when it does not exists in the database")
    @Test
    void findById_notFoundEntity() {
        Long orderId = 1L;

        when(orderJpaRepository.findById(orderId)).thenThrow(EntityNotFound.class);

        assertThrows(EntityNotFound.class,
                () -> orderJpaService.findById(orderId));

    }

    @DisplayName("Testing private method getTotalOfEachProduct")
    @Test
    void getTotalOfEachProduct() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        when(productRepository.findById(2001L)).thenReturn(Product.builder().id(2001L).build());
        when(productRepository.findById(2002L)).thenReturn(Product.builder().id(2002L).build());

        List<Long> productIds = Arrays.asList(2001L, 2002L, 2001L, 2001L, 2001L, 2001L, 2002L);

        Method method = OrderJpaServiceImpl.class.getDeclaredMethod("getTotalQuantityOfEachProduct", List.class);
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<Long, Long> result = (Map<Long, Long>) method.invoke(orderJpaService, productIds);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(5L, result.get(2001L));
        assertEquals(2L, result.get(2002L));
    }

    @DisplayName("Testing private method getTotalOfProducts using reflection")
    @Test
    @Disabled
    void getTotalOfProducts() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, JsonProcessingException {
        BigDecimal gbpTotal = usdConverter.convertAmountFromTo(gbpCurrency.getCurrencyCode()
                , DESTINATION_CURRENCY_CODE, BigDecimal.valueOf(500));
        BigDecimal expectedTotal = gbpTotal.add(usdConverter.convertAmountFromTo(mxnCurrency.getCurrencyCode()
                , DESTINATION_CURRENCY_CODE, BigDecimal.valueOf(400)));

        when(productRepository.findById(2001L)).thenReturn(product1);
        when(productRepository.findById(2002L)).thenReturn(product2);

        when(price1.getAmount()).thenReturn(BigDecimal.valueOf(100));
        when(price2.getAmount()).thenReturn(BigDecimal.valueOf(200));

        when(product1.getPrice()).thenReturn(price1);
        when(product2.getPrice()).thenReturn(price2);

        when(price1.getCurrencyCode()).thenReturn(gbpCurrency);
        when(price2.getCurrencyCode()).thenReturn(mxnCurrency);

        List<Long> productIds = Arrays.asList(2001L, 2002L, 2001L, 2001L, 2001L, 2001L, 2002L);

        Method method = OrderJpaServiceImpl.class.getDeclaredMethod("getTotalOfProducts", List.class);
        method.setAccessible(true);

        BigDecimal result = (BigDecimal) method.invoke(orderJpaService, productIds);

        assertNotNull(result);
        assertTrue(Math.abs(result.subtract(expectedTotal).doubleValue()) < 1);
    }


    @DisplayName("Test private method getTotalOfShipment using reflection")
    @Test
    @Disabled
    void getTotalOfShipmentTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, JsonProcessingException {
        Long shipmentId = 1L;
        BigDecimal expectedValue = usdConverter.convertAmountFromTo(gbpCurrency.getCurrencyCode(), DESTINATION_CURRENCY_CODE, BigDecimal.valueOf(100));

        when(shippingInformationService.getShippingInformation(shipmentId))
                .thenReturn(shippingInformation);
        when(shippingInformation.getShippingCost()).thenReturn(price1);
        when(price1.getAmount()).thenReturn(BigDecimal.valueOf(100));

        when(price1.getCurrencyCode()).thenReturn(gbpCurrency);

        Method method = OrderJpaServiceImpl.class.getDeclaredMethod("getTotalOfShipment", Long.class);
        method.setAccessible(true);

        BigDecimal result = (BigDecimal) method.invoke(orderJpaService, shipmentId);

        assertNotNull(result);
        assertEquals(expectedValue, result);
    }

}
