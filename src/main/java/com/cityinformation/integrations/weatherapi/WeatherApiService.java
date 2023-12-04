package com.cityinformation.integrations.weatherapi;

import com.cityinformation.integrations.weatherapi.dto.CityRealTimeInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherApiService {

    private final WeatherApiClient weatherApiClient;

    private static final String SERVICE_CURRENTLY_UNAVAILABLE = "Service for gathering real-time information for city is currently unavailable.";
    private static final String NO_INFORMATION_FOR_PROVIDED_CITY = "There is no information for provided city.";

    @Value("${integrations.weatherApi.apiKey}")
    private String apiKey;

    public String getCityTemperature(String cityName) {

        ResponseEntity<CityRealTimeInfo> response = weatherApiClient.getCityRealTimeInformation(apiKey, cityName);

        if (response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
            log.error("WeatherAPI service not available..");
            return SERVICE_CURRENTLY_UNAVAILABLE;
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            log.error("Request for getting the information for city with name = {} returned response status code 400.", cityName);
            return NO_INFORMATION_FOR_PROVIDED_CITY;
        }

        return Objects.requireNonNull(response.getBody()).current().temp_c();
    }
}
