package com.cityinformation.general.exceptions;

public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException(String name, String country) {
        super(String.format("City %s within country %s already exists!", name, country));
    }
}
