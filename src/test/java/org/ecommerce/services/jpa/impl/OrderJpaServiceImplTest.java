package org.ecommerce.services.jpa.impl;

import org.ecommerce.models.Product;
import org.ecommerce.services.OperationsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderJpaServiceImplTest {

    @Mock
    private OperationsService<Product, Long> operationsService;

    @InjectMocks
    private OrderJpaServiceImpl orderJpaService;

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
}
