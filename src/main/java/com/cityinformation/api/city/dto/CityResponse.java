package com.cityinformation.api.city.dto;

public record CityResponse(
        Integer id,
        String name,
        String country,
        String stateOrRegion,
        Integer population
) {
}
