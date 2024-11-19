package org.ecommerce.services.jpa.impl;

import org.ecommerce.models.Price;
import org.ecommerce.models.Product;
import org.ecommerce.models.ShippingInformation;
import org.ecommerce.services.OperationsService;
import org.ecommerce.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderJpaServiceImplTest {

    @Mock
    private ProductService operationsService;

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


    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @DisplayName("Testing private method getTotalOfEachProduct")
    @Test
    void getTotalOfEachProduct() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        when(operationsService.findById(2001L)).thenReturn(Product.builder().id(2001L).build());
        when(operationsService.findById(2002L)).thenReturn(Product.builder().id(2002L).build());

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
        when(operationsService.findById(2001L)).thenReturn(product1);
        when(operationsService.findById(2002L)).thenReturn(product2);

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
