package org.ecommerce.repositories.jpa;

import org.ecommerce.models.BillingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingInformationRepository extends JpaRepository <BillingInformation, Long> {
}
