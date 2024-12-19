package com.airbnb.annotation;

import com.airbnb.validator.CheckDatesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckDatesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDates {
    String message() default "Invalid dates";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}