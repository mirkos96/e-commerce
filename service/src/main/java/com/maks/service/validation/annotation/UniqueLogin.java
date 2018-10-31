package com.maks.service.validation.annotation;

import com.maks.service.validation.UniqueLoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginValidator.class)
public @interface UniqueLogin {
    String message() default "This login already exists, please try another one";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
