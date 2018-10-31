package com.maks.service.validation.annotation;

import com.maks.service.validation.AmountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AmountValidator.class)
public @interface Amount {
    String message() default "Invalid amount was entered (Range between: 1-10)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
