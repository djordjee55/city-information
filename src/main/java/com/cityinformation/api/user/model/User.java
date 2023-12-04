package com.cityinformation.api.user.model;

public record User(
        Integer id,
        String username,
        String encodedPassword
) {
}
