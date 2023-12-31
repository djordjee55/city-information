package com.cityinformation.api.city;

import com.cityinformation.api.city.model.City;
import com.cityinformation.api.city.model.CityEntity;
import com.cityinformation.general.exceptions.CityAlreadyExistsException;
import com.cityinformation.general.exceptions.CityNotFoundException;
import com.cityinformation.integrations.weatherapi.WeatherApiService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    private static final Faker faker = new Faker();

    @Mock
    CityRepository cityRepository;

    @Mock
    WeatherApiService weatherApiService;

    @InjectMocks
    CityService cityService;

    @Test
    void getAllCities_thenReturnListOfCities() {

        List<CityEntity> cityEntityList = List.of(CityEntity.builder().build());
        when(cityRepository.findAll()).thenReturn(cityEntityList);
        when(weatherApiService.getCityTemperature(any())).thenReturn(faker.number().digit());

        List<City> response = cityService.getAllCities();

        verify(cityRepository).findAll();
        verify(weatherApiService, times(cityEntityList.size())).getCityTemperature(any());
        assertEquals(cityEntityList.size(), response.size());
    }

    @Test
    void getCityById_whenCityWithProvidedIdExists_thenReturnCity() {

        CityEntity cityEntity = CityEntity.builder()
                .name(faker.name().name())
                .build();
        when(cityRepository.findById(any())).thenReturn(Optional.of(cityEntity));
        when(weatherApiService.getCityTemperature(any())).thenReturn(faker.number().digit());

        City city = cityService.getCityById(faker.number().randomDigit());

        verify(cityRepository).findById(any());
        verify(weatherApiService).getCityTemperature(any());
        assertEquals(cityEntity.getName(), city.getName());
    }

    @Test
    void getCityById_whenCityWithProvidedIdDoesNotExists_thenThrowCityNotFoundException() {

        when(cityRepository.findById(any())).thenThrow(new CityNotFoundException(faker.number().randomDigit()));

        assertThrows(CityNotFoundException.class, () -> cityService.getCityById(faker.number().randomDigit()));
    }

    @Test
    void createCity_whenCityNameAndRegionCombinationIsUnique_thenSuccessfullyCreateCity() {

        City city = City.builder()
                .name(faker.name().name())
                .build();
        when(cityRepository.existsByNameAndCountry(any(), any())).thenReturn(false);

        cityService.createCity(city);

        verify(cityRepository).existsByNameAndCountry(any(), any());
        verify(cityRepository).save(any());
    }

    @Test
    void createCity_whenCityNameAndRegionCombinationAlreadyExists_thenThrowCityAlreadyExistsException() {

        City city = City.builder()
                .name(faker.name().name())
                .build();
        when(cityRepository.existsByNameAndCountry(any(), any())).thenReturn(true);

        assertThrows(CityAlreadyExistsException.class, () -> cityService.createCity(city));
    }

    @Test
    void updateCity_whenUpdatedCityNameAndRegionCombinationIsUniqueAndIdIsValid_thenSuccessfullyUpdateCity() {

    }

    @Test
    void updateCity_whenCityWithProvidedIdDoesNotExists_thenThrowCityNotFoundException() {

        City city = City.builder().build();
        when(cityRepository.findById(any())).thenThrow(new CityNotFoundException(faker.number().randomDigit()));

        assertThrows(CityNotFoundException.class, () -> cityService.updateCity(faker.number().randomDigit(), city));
    }

    @Test
    void updateCity_whenUpdatedCityNameAndRegionCombinationAlreadyExists_thenThrowCityAlreadyExistsException() {

        City city = City.builder()
                .name(faker.name().name())
                .build();
        CityEntity cityEntity = CityEntity.builder()
                .name(faker.name().name())
                .build();
        when(cityRepository.findById(any())).thenReturn(Optional.of(cityEntity));
        when(cityRepository.existsByNameAndCountry(any(), any())).thenReturn(true);

        assertThrows(CityAlreadyExistsException.class, () -> cityService.updateCity(faker.number().randomDigit(), city));
    }

    @Test
    void deleteCity_whenCityWithProvidedIdExists_thenSuccessfullyDeleteCity() {

        CityEntity cityEntity = CityEntity.builder()
                .name(faker.name().name())
                .build();
        when(cityRepository.findById(any())).thenReturn(Optional.of(cityEntity));
        doNothing().when(cityRepository).delete(any());

        cityService.deleteCity(faker.number().randomDigit());

        verify(cityRepository).findById(any());
        verify(cityRepository).delete(any());
    }

    @Test
    void deleteCity_whenCityWithProvidedIdDoesNotExists_thenThrowCityNotFoundException() {

        when(cityRepository.findById(any())).thenThrow(new CityNotFoundException(faker.number().randomDigit()));

        assertThrows(CityNotFoundException.class, () -> cityService.deleteCity(faker.number().randomDigit()));
    }
}
