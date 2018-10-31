package com.maks.service.validation;

import com.maks.service.validation.annotation.NameSurname;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameSurnameValidator implements ConstraintValidator<NameSurname, String>{
    @Override
    public void initialize(NameSurname constraintAnnotation) {
    }

    @Override
    public boolean isValid(String nameSurname, ConstraintValidatorContext context) {
        return nameSurname != null && nameSurname.matches("[A-Z][a-z]{1,11} [A-Z][a-z]{1,11}");
    }
}
