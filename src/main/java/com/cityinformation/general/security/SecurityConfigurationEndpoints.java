package com.cityinformation.general.security;

public final class SecurityConfigurationEndpoints {

    private static final String[] APP_OPEN_ENDPOINTS = {
            "/auth/**"
    };

    private SecurityConfigurationEndpoints() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String[] openEndpoints() {
        return APP_OPEN_ENDPOINTS;
    }
}
