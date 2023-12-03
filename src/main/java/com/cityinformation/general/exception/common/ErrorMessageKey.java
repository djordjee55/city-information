package com.cityinformation.general.exception.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessageKey {

    BAD_REQUEST("bad.request"),
    MISSING_REQUIRED_PARAMETER("missing.required.parameter");

    private final String key;
}
