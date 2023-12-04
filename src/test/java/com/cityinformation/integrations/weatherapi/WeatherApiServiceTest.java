package com.cityinformation.integrations.weatherapi;

import com.cityinformation.integrations.weatherapi.dto.CityRealTimeInfo;
import com.cityinformation.integrations.weatherapi.dto.CurrentTemp;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.cityinformation.general.constants.Constants.NO_INFORMATION_FOR_PROVIDED_CITY;
import static com.cityinformation.general.constants.Constants.SERVICE_CURRENTLY_UNAVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherApiServiceTest {

    private static final Faker faker = new Faker();

    @Mock
    WeatherApiClient weatherApiClient;

    @InjectMocks
    WeatherApiService weatherApiService;

    @Test
    void getCityTemperature_whenServiceIsAvailableAndHasProvidedCityInformation_thenReturnTemperature() {

        String currentTemperature = faker.number().digit();
        CityRealTimeInfo currentTemperatureDto = new CityRealTimeInfo(new CurrentTemp(currentTemperature));
        ResponseEntity<CityRealTimeInfo> clientResponse = new ResponseEntity<>(currentTemperatureDto, HttpStatus.OK);
        when(weatherApiClient.getCityRealTimeInformation(any(), any())).thenReturn(clientResponse);

        String response = weatherApiService.getCityTemperature(faker.name().name());

        verify(weatherApiClient).getCityRealTimeInformation(any(), any());
        assertEquals(currentTemperature, response);
    }

    @Test
    void getCityTemperature_whenServiceIsUnavailable_thenReturnProperMessage() {

        ResponseEntity<CityRealTimeInfo> clientResponse = new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        when(weatherApiClient.getCityRealTimeInformation(any(), any())).thenReturn(clientResponse);

        String response = weatherApiService.getCityTemperature(faker.name().name());

        verify(weatherApiClient).getCityRealTimeInformation(any(), any());
        assertEquals(SERVICE_CURRENTLY_UNAVAILABLE, response);
    }

    @Test
    void getCityTemperature_whenThereIsNoInformationForProvidedCityName_thenReturnTemperature() {

        ResponseEntity<CityRealTimeInfo> clientResponse = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        when(weatherApiClient.getCityRealTimeInformation(any(), any())).thenReturn(clientResponse);

        String response = weatherApiService.getCityTemperature(faker.name().name());

        verify(weatherApiClient).getCityRealTimeInformation(any(), any());
        assertEquals(NO_INFORMATION_FOR_PROVIDED_CITY, response);
    }
}
