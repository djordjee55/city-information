package com.cityinformation.general.constants;

public final class Constants {

    public static final String CITY_AND_COUNTRY_REGEX = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
    public static final String INVALID_CITY_NAME_MESSAGE = "City name is invalid, it should contain only letters, dashes and spaces.";
    public static final String INVALID_COUNTRY_MESSAGE = "Country name is invalid, it should contain only letters, dashes and spaces.";

    private Constants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
