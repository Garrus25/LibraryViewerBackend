package com.example.libraryviewerbackend.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailAddressValidator implements
        ConstraintValidator<EmailAddress, String> {

    private static final String EMAIL_REGULAR_EXPRESSION =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    @Override
    public void initialize(EmailAddress contactNumber) {}

    @Override
    public boolean isValid(String emailAddress, ConstraintValidatorContext cxt) {
        return Pattern.compile(EMAIL_REGULAR_EXPRESSION)
                .matcher(emailAddress)
                .matches();
    }

}