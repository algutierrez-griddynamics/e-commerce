package org.ecommerce.services.jpa.impl;

import jakarta.persistence.EntityManager;
import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.mappers.BuildOrderFromDTORequest;
import org.ecommerce.mappers.OrderDTOMapper;
import org.ecommerce.models.Order;
import org.ecommerce.models.Price;
import org.ecommerce.models.Product;
import org.ecommerce.models.ShippingInformation;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.services.responses.CreateOrderResponse;
import org.ecommerce.repositories.jpa.OrderJpaRepository;
import org.ecommerce.services.ProductService;
import org.ecommerce.services.jpa.validators.OrderValidatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderJpaServiceImplTest {

    @Mock
    private OrderDTOMapper orderDTOMapper;

    @Mock
    private ProductService productRepository;

    @Mock
    private ShippingJpaInformationImpl shippingInformationService;

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

    @DisplayName("Assess create service method implementation")
    @Test
    void create() {
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

        when(shippingInformationService.findById(anyLong())).thenReturn(shippingInformation);

        when(orderJpaRepository.save(any(Order.class))).thenReturn(order);
        when(orderJpaRepository.findById(orderId)).thenReturn(Optional.of(order));

        doNothing().when(entityManager).clear();

        when(stockService.getStockOfProduct(any(Product.class))).thenReturn(1);
        doNothing().when(stockService).setStockOfProduct(any(Product.class), anyInt());


        AtomicReference<CreateOrderResponse> createOrderResponse = new AtomicReference<>();

        assertDoesNotThrow(() -> createOrderResponse.set(orderJpaService.create(createRequest)));
        assertNotNull(createOrderResponse);
    }

    @DisplayName("Assess Update service method implementation")
    @Test
    void update() {
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
    }

    @DisplayName("Assess create service method implementation")
    @Test
    void findById() {
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
    void getTotalOfProducts() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        when(productRepository.findById(2001L)).thenReturn(product1);
        when(productRepository.findById(2002L)).thenReturn(product2);

        when(price1.getAmount()).thenReturn(BigDecimal.valueOf(100));
        when(price2.getAmount()).thenReturn(BigDecimal.valueOf(200));

        when(product1.getPrice()).thenReturn(price1);
        when(product2.getPrice()).thenReturn(price2);

        List<Long> productIds = Arrays.asList(2001L, 2002L, 2001L, 2001L, 2001L, 2001L, 2002L);

        Method method = OrderJpaServiceImpl.class.getDeclaredMethod("getTotalOfProducts", List.class);
        method.setAccessible(true);

        BigDecimal result = (BigDecimal) method.invoke(orderJpaService, productIds);

        assertNotNull(result);
        assertEquals(new BigDecimal(900), result);
    }


    @DisplayName("Test private method getTotalOfShipment using reflection")
    @Test
    void getTotalOfShipmentTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Long shipmentId = 1L;

        when(shippingInformationService.findById(shipmentId))
                .thenReturn(shippingInformation);
        when(shippingInformation.getShippingCost()).thenReturn(price1);
        when(price1.getAmount()).thenReturn(BigDecimal.valueOf(100));

        Method method = OrderJpaServiceImpl.class.getDeclaredMethod("getTotalOfShipment", Long.class);
        method.setAccessible(true);

        BigDecimal result = (BigDecimal) method.invoke(orderJpaService, shipmentId);

        assertNotNull(result);
        assertEquals(new BigDecimal(100), result);
    }

}
