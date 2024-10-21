package org.ecommerce.repositories.jpa;

import org.ecommerce.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
}
