package org.ecommerce.services.jpa.impl;

import jakarta.persistence.EntityNotFoundException;
import org.ecommerce.enums.Error;
import org.ecommerce.models.ShippingInformation;
import org.ecommerce.repositories.jpa.ShippingInformationJpaRepository;
import org.ecommerce.services.jpa.ShippingInformationI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingJpaInformationImpl implements ShippingInformationI {

    private final ShippingInformationJpaRepository shippingInformationRepository;

    ShippingJpaInformationImpl(ShippingInformationJpaRepository shippingInformationRepository) {
        this.shippingInformationRepository = shippingInformationRepository;
    }

    @Override
    public ShippingInformation create(ShippingInformation entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ShippingInformation update(ShippingInformation entity, Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ShippingInformation> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ShippingInformation findById(Long id) {
        return shippingInformationRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(Error.ENTITY_NOT_FOUND.getDescription())
                );
    }

    @Override
    public boolean validateShippingInformation(ShippingInformation shippingInformation) {
        return true;
    }
}
