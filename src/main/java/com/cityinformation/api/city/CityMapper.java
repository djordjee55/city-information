package com.cityinformation.api.city;

import com.cityinformation.api.city.dto.CityResponse;
import com.cityinformation.api.city.dto.CreateCityRequest;
import com.cityinformation.api.city.dto.UpdateCityRequest;
import com.cityinformation.api.city.model.City;
import com.cityinformation.api.city.model.CityEntity;

public final class CityMapper {

    private CityMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static City fromCreateRequest(CreateCityRequest createCityRequest) {
        return City.builder()
                .name(createCityRequest.name())
                .country(createCityRequest.country())
                .stateOrRegion(createCityRequest.stateOrRegion())
                .population(createCityRequest.population())
                .build();
    }

    public static City fromUpdateRequest(UpdateCityRequest updateCityRequest) {
        return City.builder()
                .name(updateCityRequest.name())
                .country(updateCityRequest.country())
                .stateOrRegion(updateCityRequest.stateOrRegion())
                .population(updateCityRequest.population())
                .build();
    }

    public static City fromEntity(CityEntity cityEntity) {
        return City.builder()
                .id(cityEntity.getId())
                .name(cityEntity.getName())
                .country(cityEntity.getCountry())
                .stateOrRegion(cityEntity.getStateOrRegion())
                .population(cityEntity.getPopulation())
                .build();
    }

    public static CityEntity toEntity(City city) {
        return CityEntity.builder()
                .id(city.getId())
                .name(city.getName())
                .country(city.getCountry())
                .stateOrRegion(city.getStateOrRegion())
                .population(city.getPopulation())
                .build();
    }

    public static CityResponse toResponse(City city) {
        return new CityResponse(city.getId(), city.getName(), city.getCountry(),
                city.getStateOrRegion(), city.getPopulation());
    }
}
