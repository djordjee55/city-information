package com.cityinformation.general.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("com.cityinformation")
public class CityInfoProperties {

    /**
     * Secret key used for signing the JWT tokens
     */
    private final String signingSecret;
}
