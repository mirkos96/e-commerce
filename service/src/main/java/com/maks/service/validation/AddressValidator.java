package com.maks.service.validation;

import com.maks.service.validation.annotation.Address;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address, String> {
    @Override
    public void initialize(Address constraintAnnotation) {
    }

    @Override
    public boolean isValid(String address, ConstraintValidatorContext context) {
        return address != null && address
                .matches("[aA-zZ]{3,15} [0-9]{1,3}-[0-9]{1,3} [aA-zZ]{6,8}");
    }
}
