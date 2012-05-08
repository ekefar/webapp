package com.teamdev.webapp1.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Author: Alexander Serebriyan
 * Date: 08.05.12
 */
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={OrderConstraintValidator.class, CartItemConstraintValidator.class})
@Documented
public @interface AmountAcceptable {
    String message() default "{com.teamdev.model.order.AmountAcceptable}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
