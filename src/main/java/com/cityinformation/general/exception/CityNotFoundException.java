package com.cityinformation.general.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(Integer id) {
        super(String.format("City with id=%d not found!", id));
    }
}
