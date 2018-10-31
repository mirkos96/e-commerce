package com.maks.service.validation.annotation;

import com.maks.service.validation.NameSurnameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = NameSurnameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface NameSurname {
    String message() default "Invalid name or surname";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
