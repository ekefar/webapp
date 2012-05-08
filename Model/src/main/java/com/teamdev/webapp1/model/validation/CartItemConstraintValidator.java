package com.teamdev.webapp1.model.validation;

import com.teamdev.webapp1.model.user.CartItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Author: Alexander Serebriyan
 * Date: 08.05.12
 */
public class CartItemConstraintValidator implements ConstraintValidator<AmountAcceptable, CartItem> {
    @Override
    public void initialize(AmountAcceptable constraintAnnotation) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(CartItem value, ConstraintValidatorContext context) {
        return value.getOffer().getAmount() > value.getAmount();
    }
}
