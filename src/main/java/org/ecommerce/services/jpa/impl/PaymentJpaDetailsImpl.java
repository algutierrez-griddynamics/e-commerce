package org.ecommerce.services.jpa.impl;

import org.ecommerce.models.PaymentDetails;
import org.ecommerce.services.jpa.PaymentDetailsI;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaymentJpaDetailsImpl implements PaymentDetailsI {

    @Override
    public PaymentDetails create(PaymentDetails entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PaymentDetails update(PaymentDetails entity, Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PaymentDetails> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PaymentDetails findById(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean validateData(PaymentDetails paymentDetails) {
        return true;
    }
}
