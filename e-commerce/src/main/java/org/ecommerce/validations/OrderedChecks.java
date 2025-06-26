package org.ecommerce.validations;

import jakarta.validation.GroupSequence;
import org.ecommerce.validations.constraints.CurrentDay;
import org.ecommerce.validations.constraints.ValidOrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@GroupSequence({NotNull.class, Positive.class, ValidOrderStatus.class,
        CurrentDay.class, DateTimeFormat.class, PositiveOrZero.class})
public interface OrderedChecks {
}
