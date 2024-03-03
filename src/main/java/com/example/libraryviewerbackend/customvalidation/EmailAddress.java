package com.example.libraryviewerbackend.customvalidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailAddressValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddress {
    String message() default "Invalid format of email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}