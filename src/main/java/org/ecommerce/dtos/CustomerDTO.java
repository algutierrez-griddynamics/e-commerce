package org.ecommerce.dtos;

public record CustomerDTO (
        String firstName,
        String lasName,
        String email,
        String phoneNumber
) {
}
