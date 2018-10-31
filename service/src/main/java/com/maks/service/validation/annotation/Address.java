package com.maks.service.validation.annotation;

import com.maks.service.validation.AddressValidator;
import org.springframework.context.annotation.PropertySource;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
public @interface Address {
    String message() default "Invalid address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
