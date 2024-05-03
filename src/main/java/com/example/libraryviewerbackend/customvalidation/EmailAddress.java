package com.example.libraryviewerbackend.customvalidation;

import com.example.libraryviewerbackend.utils.UserMessages;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailAddressValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddress {
    String message() default UserMessages.INVALID_FORMAT_OF_EMAIL_ADDRESS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}