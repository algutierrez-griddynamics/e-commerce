package org.ecommerce.services.jpa.impl;

import jakarta.persistence.EntityNotFoundException;
import org.ecommerce.models.BillingInformation;
import org.ecommerce.repositories.jpa.BillingInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingInformationImpl implements BillingInformationI {

    private final BillingInformationRepository billingInformationRepository;

    public BillingInformationImpl(BillingInformationRepository billingInformationRepository) {
        this.billingInformationRepository = billingInformationRepository;
    }

    @Override
    public BillingInformation create(BillingInformation entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BillingInformation update(BillingInformation entity, Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BillingInformation> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BillingInformation findById(Long billingInformationId) {
        return billingInformationRepository.findById(billingInformationId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean validateBillingInformation() {
        return false;
    }
}
