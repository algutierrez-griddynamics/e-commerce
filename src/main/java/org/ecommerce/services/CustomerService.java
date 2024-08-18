package org.ecommerce.services;

import org.ecommerce.models.Customer;
import org.ecommerce.repositories.CustomerRepository;
import org.ecommerce.util.CrudOperations;

import java.util.List;

public class CustomerService implements CrudOperations<Customer> {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        return null;
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Customer update(Long id, Customer customerDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Customer> findAll() {
        return null;
    }


}
