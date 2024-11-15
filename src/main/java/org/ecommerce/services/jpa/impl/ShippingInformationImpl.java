package org.ecommerce.services.jpa.impl;

import jakarta.persistence.EntityNotFoundException;
import org.ecommerce.enums.Error;
import org.ecommerce.models.ShippingInformation;
import org.ecommerce.repositories.jpa.ShippingInformationRepository;
import org.ecommerce.services.OperationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingInformationImpl implements OperationsService<ShippingInformation, Long> {

    private final ShippingInformationRepository shippingInformationRepository;

    ShippingInformationImpl(ShippingInformationRepository shippingInformationRepository) {
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
}
