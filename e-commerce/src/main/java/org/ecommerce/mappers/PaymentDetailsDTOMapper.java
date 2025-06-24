package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.PaymentDetailsDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.MappingException;
import org.ecommerce.models.PaymentDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class PaymentDetailsDTOMapper implements Function<PaymentDetails, PaymentDetailsDTO> {

    @Override
    public PaymentDetailsDTO apply(PaymentDetails paymentDetails) {
        return Optional.ofNullable(paymentDetails)
                .map(pd -> {
                            int lastIndex = pd.getCardNumber().length() - 1;
                            int beginIndex = lastIndex - 4;
                            return new PaymentDetailsDTO(
                                    pd.getPaymentMethodType(),
                                    pd.getCardNumber().substring(beginIndex, lastIndex),
                                    pd.getCardHolderName());
                        }
                ).orElseThrow(
                        () -> new MappingException(getClass().getSimpleName(), Error.MAPPING_EXCEPTION.getDescription())
                );
    }
}
