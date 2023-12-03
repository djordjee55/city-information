package com.cityinformation.api.city;

import com.cityinformation.api.city.model.City;
import com.cityinformation.api.city.model.CityEntity;
import com.cityinformation.general.exception.CityAlreadyExistsException;
import com.cityinformation.general.exception.CityNotFoundException;
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

    @InjectMocks
    CityService cityService;

    @Test
    void getAllCities_returnListOfCities() {

        List<CityEntity> cityEntityList = List.of(CityEntity.builder().build());
        when(cityRepository.findAll()).thenReturn(cityEntityList);

        List<City> response = cityService.getAllCities();

        verify(cityRepository).findAll();
        assertEquals(cityEntityList.size(), response.size());
    }

    @Test
    void getCityById_whenCityWithProvidedIdExists_returnCity() {

        CityEntity cityEntity = CityEntity.builder()
                .name(faker.name().name())
                .build();
        when(cityRepository.findById(any())).thenReturn(Optional.of(cityEntity));

        City city = cityService.getCityById(faker.number().randomDigit());

        verify(cityRepository).findById(any());
        assertEquals(cityEntity.getName(), city.getName());
    }

    @Test
    void getCityById_whenCityWithProvidedIdDoesNotExists_throwCityNotFoundException() {

        when(cityRepository.findById(any())).thenThrow(new CityNotFoundException(faker.number().randomDigit()));

        assertThrows(CityNotFoundException.class, () -> cityService.getCityById(faker.number().randomDigit()));
    }

    @Test
    void createCity_whenCityNameAndRegionCombinationIsUnique_successfullyCreateCity() {

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
    void updateCity_whenUpdatedCityNameAndRegionCombinationIsUniqueAndIdIsValid_successfullyUpdateCity() {

    }

    @Test
    void updateCity_whenCityWithProvidedIdDoesNotExists_throwCityNotFoundException() {

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
    void deleteCity_whenCityWithProvidedIdExists_successfullyDeleteCity() {

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
    void deleteCity_whenCityWithProvidedIdDoesNotExists_throwCityNotFoundException() {

        when(cityRepository.findById(any())).thenThrow(new CityNotFoundException(faker.number().randomDigit()));

        assertThrows(CityNotFoundException.class, () -> cityService.deleteCity(faker.number().randomDigit()));
    }
}
