package com.teamdev.webapp1.model.validation;

import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.order.OfferStates;
import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Author: Alexander Serebriyan
 * Date: 08.05.12
 */
public class OfferConstraintValidator implements ConstraintValidator<AmountAcceptable, Offer> {


    @Override
    public void initialize(AmountAcceptable constraintAnnotation) {

    }

    @Override
    public boolean isValid(Offer value, ConstraintValidatorContext context) {

        return value.getState() != OfferStates.ACTIVE || value.getAmount() > 0;

    }
}
