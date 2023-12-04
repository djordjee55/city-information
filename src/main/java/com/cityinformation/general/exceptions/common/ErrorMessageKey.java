package com.cityinformation.general.exceptions.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessageKey {

    BAD_REQUEST("bad.request"),
    MISSING_REQUIRED_PARAMETER("missing.required.parameter"),
    INVALID_CREDENTIALS("invalid.credentials");

    private final String key;
}
