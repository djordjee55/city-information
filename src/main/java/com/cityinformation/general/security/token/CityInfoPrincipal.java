package com.cityinformation.general.security.token;

import java.security.Principal;

public record CityInfoPrincipal(Integer userId) implements Principal {

    @Override
    public String getName() {
        return userId.toString();
    }
}