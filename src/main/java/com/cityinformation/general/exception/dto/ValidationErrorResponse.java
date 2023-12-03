package com.cityinformation.general.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.Optional;


@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResponse {

    private String field;
    private String rejectedValue;
    private String defaultMessage;

    public static ValidationErrorResponse fromFieldError(FieldError fieldError) {
        return ValidationErrorResponse.builder()
                .field(fieldError.getField())
                .rejectedValue(Optional.ofNullable(fieldError.getRejectedValue()).map(Object::toString).orElse(""))
                .defaultMessage(fieldError.getDefaultMessage())
                .build();
    }

    public static ValidationErrorResponse fromConstraintViolation(ConstraintViolation<?> constraintViolation) {
        return ValidationErrorResponse.builder()
                .field(constraintViolation.getPropertyPath().toString())
                .rejectedValue(Optional.ofNullable(constraintViolation.getInvalidValue()).map(Object::toString).orElse(""))
                .defaultMessage(constraintViolation.getMessage())
                .build();
    }
}
