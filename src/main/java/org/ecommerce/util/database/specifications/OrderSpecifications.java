package org.ecommerce.util.database.specifications;

import org.ecommerce.enums.OrderStatus;
import org.ecommerce.models.Order;
import org.ecommerce.util.Enums;
import org.springframework.data.jpa.domain.Specification;


import java.time.LocalDateTime;

public class OrderSpecifications {

    public static Specification<Order> isFromCustomerNameLike(String firstName) {
        return (root, criteriaQuery, criteriaBuilder)
                -> firstName == null || firstName.isBlank() ? null : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("customer")
                .get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<Order> isFromCityLike(String city) {
        return (root, query, criteriaBuilder)
                -> city == null || city.isBlank() ? null : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("shippingInformation")
                                .get("city")), "%" + city.toLowerCase() + "%");
    }

    public static Specification<Order> isFromCustomerIdEquals(Long customerId) {
        return (root, query, criteriaBuilder)
            -> customerId == null ? null : criteriaBuilder.equal(root.get("customer")
                .get("id"), customerId);
    }

    public static Specification<Order> isOrderStatusEquals(String orderStatus) {
        return (root, query, criteriaBuilder)
                -> orderStatus == null || !Enums.isInEnum(orderStatus.toUpperCase(), OrderStatus.class)
                ? null : criteriaBuilder.equal(root.get("status"), orderStatus);
    }

    public static Specification<Order> isInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return ((root, query, criteriaBuilder) -> {
           if (startDate == null && endDate == null)
               return null;
           else if (startDate != null && endDate != null)
               return criteriaBuilder.between(root.get("orderDate"), startDate, endDate);
           else if (startDate != null)
               return criteriaBuilder.greaterThanOrEqualTo(root.get("orderDate"), startDate);
           else
               return criteriaBuilder.lessThanOrEqualTo(root.get("orderDate"), endDate);
        });
    }

    public static Specification<Order> allSpecifications(
            SpecificationParameters specificationParameters
    ) {
        return Specification.where(isFromCustomerNameLike(specificationParameters.getFirstName())
                .and(isFromCityLike(specificationParameters.getCity()))
                .and(isFromCustomerIdEquals(specificationParameters.getCustomerId()))
                .and(isOrderStatusEquals(specificationParameters.getOrderStatus()))
                .and(isInDateRange(specificationParameters.getStartDate(), specificationParameters.getEndDate())));
    }
}
