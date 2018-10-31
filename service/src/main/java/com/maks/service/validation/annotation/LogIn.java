package com.maks.service.validation.annotation;

import com.maks.service.validation.LogInValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LogInValidator.class)
public @interface LogIn {
    String message() default "Invalid email length! Too long or too small. Or check your syntax";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
