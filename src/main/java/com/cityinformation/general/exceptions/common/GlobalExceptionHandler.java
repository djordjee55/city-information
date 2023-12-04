package com.cityinformation.general.exceptions.common;

import com.cityinformation.general.exceptions.CityNotFoundException;
import com.cityinformation.general.exceptions.InvalidCredentialException;
import com.cityinformation.general.exceptions.dto.ApiErrorResponse;
import com.cityinformation.general.exceptions.dto.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        log.error("MethodArgumentNotValidException: {}", exception.getBindingResult().getFieldError().getField());

        List<ValidationErrorResponse> validationErrors =
                exception.getBindingResult().getFieldErrors().stream().map(ValidationErrorResponse::fromFieldError)
                        .toList();

        return ApiErrorResponse.builder()
                .fieldValidationErrors(validationErrors)
                .messageKey(ErrorMessageKey.BAD_REQUEST.getKey())
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiErrorResponse missingServletRequestParameterException(MissingServletRequestParameterException exception) {

        log.error("MissingServletRequestParameterException: {}", exception.getParameterName());

        String message = "Required parameter '%s' is not present.".formatted(exception.getParameterName());

        return ApiErrorResponse.builder()
                .messageKey(ErrorMessageKey.BAD_REQUEST.getKey())
                .message(message)
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityNotFoundException.class)
    public ApiErrorResponse cityNotFoundException(CityNotFoundException exception) {

        log.error("CityNotFoundException: {}", exception.getMessage());

        return ApiErrorResponse.builder()
                .messageKey(ErrorMessageKey.BAD_REQUEST.getKey())
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialException.class)
    public ApiErrorResponse invalidCredentialException(InvalidCredentialException exception) {

        log.error("InvalidCredentialException: {}", exception.getMessage());

        return ApiErrorResponse.builder()
                .messageKey(ErrorMessageKey.INVALID_CREDENTIALS.getKey())
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ApiErrorResponse runtimeException(RuntimeException exception) {

        log.error("RuntimeException: {}", exception.getMessage());

        return ApiErrorResponse.builder()
                .messageKey(ErrorMessageKey.BAD_REQUEST.getKey())
                .message(exception.getMessage())
                .build();
    }
}
