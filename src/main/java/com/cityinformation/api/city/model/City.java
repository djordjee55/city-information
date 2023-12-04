package com.cityinformation.api.city.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class City {

    private Integer id;

    private String name;

    private String country;

    private String stateOrRegion;

    private Integer population;

    private String currentTempC;
}
