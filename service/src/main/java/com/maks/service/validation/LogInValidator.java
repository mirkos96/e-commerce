package com.maks.service.validation;

import com.maks.service.validation.annotation.LogIn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LogInValidator implements ConstraintValidator<LogIn, String>{
    @Override
    public void initialize(LogIn annotation) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        return login != null &&
                login.matches
                        ("[aA0-zZ9]{1,10}-[0aA-zZ9]{1,10}@(gmail|tut|yahoo|mail|yandex).[a-z]{2,3}");
    }
}
