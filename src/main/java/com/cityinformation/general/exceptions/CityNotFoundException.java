package com.cityinformation.general.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(Integer id) {
        super(String.format("City with id=%d not found!", id));
    }
}
