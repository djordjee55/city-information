package com.cityinformation.general.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    @NonNull
    private String message;

    private String messageKey;
    private List<ValidationErrorResponse> fieldValidationErrors;
}
