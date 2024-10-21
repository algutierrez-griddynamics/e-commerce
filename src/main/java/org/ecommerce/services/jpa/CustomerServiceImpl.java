package org.ecommerce.services.jpa;

import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.models.Customer;
import org.ecommerce.repositories.jpa.CustomerJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerServiceImpl(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Customer create(Customer entity) {
        return customerJpaRepository.save(entity);
    }

    @Override
    public Customer update(Customer entity, Long id) {
        if (Objects.nonNull(findById(id))) {
            return customerJpaRepository.save(entity);
        } else {
            throw new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription());
        }
    }

    @Override
    public void delete(Long id) {
        customerJpaRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()));
    }

    @Override
    public Boolean isValidEntity(Customer obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
