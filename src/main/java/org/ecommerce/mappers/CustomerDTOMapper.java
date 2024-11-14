package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.CustomerDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.MappingException;
import org.ecommerce.models.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return Optional.ofNullable(customer)
                .map(c -> new CustomerDTO(
                        c.getFirstName(),
                        c.getLastName(),
                        c.getEmail(),
                        c.getPhoneNumber()
                ))
                .orElseThrow(
                        () ->  new MappingException(getClass().getSimpleName(), Error.MAPPING_EXCEPTION.getDescription())
                );
    }
}
