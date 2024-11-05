package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.CustomerDTO;
import org.ecommerce.models.Customer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return null;
    }
}
