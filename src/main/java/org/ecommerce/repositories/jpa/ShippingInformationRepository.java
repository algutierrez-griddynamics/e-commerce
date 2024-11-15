package org.ecommerce.repositories.jpa;

import org.ecommerce.models.ShippingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingInformationRepository extends JpaRepository <ShippingInformation, Long> {
}
