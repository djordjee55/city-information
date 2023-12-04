package com.cityinformation.api.city.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "country", "state/region", "population", "temp_c" })
public record CityResponse(
        Integer id,
        String name,
        String country,
        @JsonProperty(value = "state/region")
        String stateOrRegion,
        Integer population,
        @JsonProperty(value = "temp_c")
        String currentTempC
) {
}
