package org.ecommerce.repositories.jpa;

import org.ecommerce.enums.AvailableOrderByFields;
import org.ecommerce.enums.OrderStatus;
import org.ecommerce.models.Order;
import org.ecommerce.util.database.specifications.OrderSpecifications;
import org.ecommerce.util.database.specifications.SpecificationParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OrderJpaRepositoryTest {

    @Mock
    private Pageable pageable;

    @Autowired
    private OrderJpaRepository repository;

    private final int pageSize = 2;
    private final int pageNumber = 1;
    private SpecificationParameters specificationParameters;
    private final Sort sort = Sort.by(Sort.Direction.ASC, AvailableOrderByFields.DATE.getFieldName());


    @BeforeEach
    void setUp() {
        when(pageable.getPageNumber()).thenReturn(pageNumber);
        when(pageable.getPageSize()).thenReturn(pageSize);
        when(pageable.getSort()).thenReturn(sort);
    }


    @DisplayName("Assert that the repository is working as expected when sending pagination specifications")
    @Test
    void findAllWithPagination() {
        specificationParameters = SpecificationParameters.builder().build();

        Page<Order> orders = repository
                .findAll(
                        OrderSpecifications.allSpecifications(specificationParameters)
                        , pageable
                );

        assertAll(
                () -> {
                    assertNotNull(orders);
                    assertTrue(orders.getTotalElements() == 2 || orders.getTotalElements() == 5);
                    assertEquals(1, orders.getTotalPages());
                    assertEquals(pageSize, orders.getPageable().getPageSize());

                    assertEquals(pageNumber, orders.getPageable().getPageNumber());
                    assertEquals(sort, orders.getPageable().getSort());
                }
        );
    }

    @DisplayName("Assert that the repository is working as expected when sending pagination specifications of an existing User")
    @Test
    void findAllWithCustomSpecificationsExistingUser() {
        specificationParameters = SpecificationParameters.builder()
                .firstName("Jane")
                .city("Los")
                .orderStatus(OrderStatus.SHIPPED.name())
                .build();

        Page<Order> orders = repository
                .findAll(
                        OrderSpecifications.allSpecifications(specificationParameters)
                        , pageable
        );

        assertNotNull(orders);
        assertEquals(1, orders.getTotalElements());
    }

    @DisplayName("Assert that the repository is working as expected when sending pagination specifications of a NON existing User")
    @Test
    void findAllWithCustomSpecificationsNonExistingUser() {
        specificationParameters = SpecificationParameters.builder()
                .firstName("Jane")
                .city("Angeles")
                .orderStatus(OrderStatus.REQUESTED.name())
                .build();

        Page<Order> orders = repository
                .findAll(
                        OrderSpecifications.allSpecifications(specificationParameters)
                        , pageable
                );

        assertNotNull(orders);
        assertEquals(0, orders.getTotalElements());
    }

}