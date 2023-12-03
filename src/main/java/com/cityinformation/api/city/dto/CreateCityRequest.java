package com.cityinformation.api.city.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.cityinformation.general.constants.Constants.*;

public record CreateCityRequest(

        @NotBlank
        @Pattern(regexp = CITY_AND_COUNTRY_REGEX, message = INVALID_CITY_NAME_MESSAGE)
        String name,
        @NotBlank
        @Pattern(regexp = CITY_AND_COUNTRY_REGEX, message = INVALID_COUNTRY_MESSAGE)
        String country,
        String stateOrRegion,
        @NotNull
        @Min(1)
        Integer population
) {
}
