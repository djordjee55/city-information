package com.cityinformation.general.security.token;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

@Getter
public class VerifiedToken extends AbstractAuthenticationToken {

    private final Integer userId;

    public VerifiedToken(String userId) {
        super(Collections.emptyList());

        this.userId = Integer.valueOf(userId);
    }

    @Override
    public Object getCredentials() {
        return this.userId;
    }

    @Override
    public Object getPrincipal() {
        return this.userId;
    }
}
