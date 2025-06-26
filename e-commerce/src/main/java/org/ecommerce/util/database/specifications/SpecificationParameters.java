package org.ecommerce.util.database.specifications;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SpecificationParameters {
    private String firstName;
    private String city;
    private Long customerId;
    private String orderStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
