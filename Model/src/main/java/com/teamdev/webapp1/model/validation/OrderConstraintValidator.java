package com.teamdev.webapp1.model.validation;

import com.teamdev.webapp1.model.order.Order;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Author: Alexander Serebriyan
 * Date: 08.05.12
 */
public class OrderConstraintValidator implements ConstraintValidator<AmountAcceptable, Order> {


    @Override
    public void initialize(AmountAcceptable constraintAnnotation) {

    }

    @Override
    public boolean isValid(Order value, ConstraintValidatorContext context) {
        return value.getOffer().getAmount() > value.getAmount();
    }
}
