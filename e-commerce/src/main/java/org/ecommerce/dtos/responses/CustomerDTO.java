package org.ecommerce.dtos.responses;

public record CustomerDTO (
        String firstName,
        String lasName,
        String email,
        String phoneNumber
) {
}
