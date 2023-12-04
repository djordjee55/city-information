package com.cityinformation.integrations.weatherapi;

import com.cityinformation.integrations.weatherapi.dto.CityRealTimeInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.cityinformation.general.constants.Constants.NO_INFORMATION_FOR_PROVIDED_CITY;
import static com.cityinformation.general.constants.Constants.SERVICE_CURRENTLY_UNAVAILABLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherApiService {

    private final WeatherApiClient weatherApiClient;

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
