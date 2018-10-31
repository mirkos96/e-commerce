package com.maks.service.validation;

import com.maks.service.validation.annotation.Amount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<Amount, Integer> {
    @Override
    public void initialize(Amount constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer amount, ConstraintValidatorContext context) {
        if (amount == null || amount > 10){
            return false;
        }
        return true;
    }
}
