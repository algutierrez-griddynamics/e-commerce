package org.ecommerce.util.database.specifications;


import jakarta.persistence.criteria.*;
import org.ecommerce.enums.OrderStatus;
import org.ecommerce.models.Order;
import org.ecommerce.util.Enums;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderSpecificationsTest {

    @Mock
    private Root<Order> mockRoot;

    @Mock
    private Root<Object> mockPath;

    @Mock
    private Path<Object> mockIdPath;

    @Mock
    private CriteriaQuery<?> mockQuery;

    @Mock
    private CriteriaBuilder mockBuilder;
    
    @Mock
    private Root<Object> mockCustomerRoot;

    @Mock
    private Predicate mockPredicate;

    private final String emptyString = " ";
    private final String nullString = null;

    private final Long validId = 1L;
    private final Long nullId = null;

    private final String validOrderStatusString = OrderStatus.PLACED.name();

    private final LocalDateTime nullLocalDateTime = null;
    private final LocalDateTime validStartDateTime = LocalDateTime.now().minusDays(10);
    private final LocalDateTime validEndDateTime = LocalDateTime.now();


    @Test
    void isFromCustomerNameLikeNullEmpty() {
        Specification<Order> spec = OrderSpecifications.isFromCustomerNameLike(nullString);
        spec = OrderSpecifications.isFromCustomerNameLike(emptyString);

        assertNull(spec.toPredicate(mockRoot, mockQuery, mockBuilder));
        assertNull(spec.toPredicate(mockRoot, mockQuery, mockBuilder));
    }

    @Test
    void isFromCustomerNameLikeValidString() {
        when(mockRoot.get("customer")).thenReturn(mockCustomerRoot);
        when(mockCustomerRoot.get("firstName")).thenReturn(mockCustomerRoot);
        when(mockBuilder.like(any(), eq("%valid%"))).thenReturn(mockPredicate);

        Specification<Order> validSpecification = OrderSpecifications.isFromCustomerNameLike("valid");
        Predicate predicate = validSpecification.toPredicate(mockRoot, mockQuery, mockBuilder);

        assertAll(
                () -> {
                    assertNotNull(predicate);
                    verify(mockBuilder).like(any(), eq("%valid%"));
                    verify(mockRoot.get("customer")).get("firstName");
                }
        );
    }

    @Test
    void isFromCityLikeNullEmpty() {
        Specification<Order> spec = OrderSpecifications.isFromCityLike(nullString);
        spec = OrderSpecifications.isFromCityLike(emptyString);

        assertNull(spec.toPredicate(mockRoot, mockQuery, mockBuilder));
        assertNull(spec.toPredicate(mockRoot, mockQuery, mockBuilder));
    }

    @Test
    void isFromCityLikeValidString() {
        when(mockRoot.get("shippingInformation")).thenReturn(mockPath);
        when(mockPath.get("city")).thenReturn(mockPath);
        when(mockBuilder.like(any(), eq("%valid%"))).thenReturn(mockPredicate);

        Specification<Order> spec = OrderSpecifications.isFromCityLike("valid");
        Predicate predicate = spec.toPredicate(mockRoot, mockQuery, mockBuilder);

        assertAll(
                () -> {
                    assertNotNull(predicate);
                    verify(mockBuilder).like(any(), eq("%valid%"));
                    verify(mockBuilder).like(any(), eq("%valid%"));
                    verify(mockPath).get("city");
                }
        );
    }

    @Test
    void isFromCustomerIdEqualsNull() {
        Specification<Order> spec = OrderSpecifications.isFromCustomerIdEquals(nullId);
        assertNull(spec.toPredicate(mockRoot, mockQuery, mockBuilder));
    }

    @Test
    void isFromCustomerIdEqualsValidId() {
        when(mockRoot.get("customer")).thenReturn(mockPath);
        when(mockPath.get("id")).thenReturn(mockIdPath);
        when(mockBuilder.equal(mockIdPath, validId)).thenReturn(mockPredicate);

        Specification<Order> spec = OrderSpecifications.isFromCustomerIdEquals(validId);
        Predicate predicate = spec.toPredicate(mockRoot, mockQuery, mockBuilder);

        assertAll(
                () -> {
                    assertNotNull(predicate);
                    verify(mockRoot).get("customer");
                    verify(mockPath).get("id");
                    verify(mockBuilder).equal(mockIdPath, validId);
                }
        );

    }

    @Test
    void isOrderStatusEqualsNullEmpty() {
        Specification<Order> spec = OrderSpecifications.isOrderStatusEquals(nullString);
        spec = OrderSpecifications.isOrderStatusEquals(emptyString);

        assertNull(spec.toPredicate(mockRoot, mockQuery, mockBuilder));
        assertNull(spec.toPredicate(mockRoot, mockQuery, mockBuilder));
    }

    @Test
    void isOrderStatusEqualsValidOrderStatus() {
        try (MockedStatic<Enums> mockedEnums = mockStatic(Enums.class)) {
            mockedEnums.when(() -> Enums.isInEnum(validOrderStatusString, OrderStatus.class)).thenReturn(true);
            when(mockRoot.get("status")).thenReturn(mockPath);
            when(mockBuilder.equal(mockPath, validOrderStatusString)).thenReturn(mockPredicate);

            Specification<Order> spec = OrderSpecifications.isOrderStatusEquals(validOrderStatusString);
            Predicate predicate = spec.toPredicate(mockRoot, mockQuery, mockBuilder);

            assertAll(
                    () -> {
                        assertNotNull(predicate);
                        verify(mockRoot).get("status");
                        verify(mockBuilder).equal(mockPath, validOrderStatusString);
                    }
            );
        }
    }

    @Test
    void isInDateRangeNull() {
        Specification<Order> spec = OrderSpecifications.isInDateRange(nullLocalDateTime, nullLocalDateTime);
        assertNull(spec.toPredicate(mockRoot, mockQuery, mockBuilder));
    }

    @Test
    void isInDateRangeValidDate() {
        when(mockBuilder.between(mockRoot.get("orderDate"), validStartDateTime, validEndDateTime)).thenReturn(mockPredicate);

        Specification<Order> spec = OrderSpecifications.isInDateRange(validStartDateTime, validEndDateTime);
        Predicate predicate = spec.toPredicate(mockRoot, mockQuery, mockBuilder);

        assertAll(
                () -> {
                    assertNotNull(predicate);
                    verify(mockBuilder).between(mockRoot.get("orderDate"), validStartDateTime, validEndDateTime);
                }
        );
    }
}