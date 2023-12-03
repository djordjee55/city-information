package com.cityinformation.general.exception;

public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException(String name, String country) {
        super(String.format("City %s within country %s already exists!", name, country));
    }
}
