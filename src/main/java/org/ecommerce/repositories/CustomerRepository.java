package org.ecommerce.repositories;

import org.ecommerce.models.Customer;
import org.ecommerce.util.CrudOperations;

import java.util.List;

public class CustomerRepository implements CrudOperations<Customer> {
    @Override
    public Customer create(Customer customer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Customer getById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Customer update(Long id, Customer customer) {
        throw new UnsupportedOperationException();
    }
    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Customer> findAll() {
        throw new UnsupportedOperationException();
    }

    public String findPasswordById(Long id) {
        return "Mario123!";
    }

    public Customer updatePasswordById(Long id, String newPassword) {
        return new Customer(id, "Mario", "Ramirez", "mariojazael@gmail.com", newPassword);
    }
}
