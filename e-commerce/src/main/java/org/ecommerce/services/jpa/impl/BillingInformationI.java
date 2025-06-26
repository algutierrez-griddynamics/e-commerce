package org.ecommerce.services.jpa.impl;

import org.ecommerce.models.BillingInformation;
import org.ecommerce.services.OperationsService;

public interface BillingInformationI extends OperationsService<BillingInformation, Long> {
    public boolean validateBillingInformation();
}
