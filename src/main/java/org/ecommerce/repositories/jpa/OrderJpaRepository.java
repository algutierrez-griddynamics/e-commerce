package org.ecommerce.repositories.jpa;

import org.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}