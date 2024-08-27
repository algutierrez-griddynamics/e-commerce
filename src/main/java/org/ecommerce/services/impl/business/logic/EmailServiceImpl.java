package org.ecommerce.services.impl.business.logic;

import org.ecommerce.services.EmailService;
import org.ecommerce.util.validators.impl.Validators;

public class EmailServiceImpl implements EmailService {

    @Override
    public boolean isValidInput(String email) {
        return Validators.isValidEmail(email);
    }
}
