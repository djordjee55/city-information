package com.cityinformation.general.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.io.Serial;
import java.util.Collections;

public class AuthenticationToken extends AbstractAuthenticationToken {

    @Serial
    private static final long serialVersionUID = -2872969631154316031L;
    private final transient CityInfoPrincipal cityInfoPrincipal;

    public AuthenticationToken(Integer userId) {
        super(Collections.emptyList());
        this.setAuthenticated(true);
        this.cityInfoPrincipal = new CityInfoPrincipal(userId);
    }

    @Override
    public Object getCredentials() {
        return cityInfoPrincipal.userId();
    }

    @Override
    public Object getPrincipal() {
        return cityInfoPrincipal;
    }
}
