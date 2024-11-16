package org.ecommerce.repositories.jpa;

import org.ecommerce.models.StockEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntry, Long> {
}
