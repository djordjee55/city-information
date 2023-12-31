package com.cityinformation.api.city;

import com.cityinformation.api.city.model.City;
import com.cityinformation.api.city.model.CityEntity;
import com.cityinformation.general.exceptions.CityAlreadyExistsException;
import com.cityinformation.general.exceptions.CityNotFoundException;
import com.cityinformation.integrations.weatherapi.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final WeatherApiService weatherApiService;
    private final CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll().stream()
                .map(city -> CityMapper.fromEntityWithTemperature(city, getCityCurrentTemperature(city)))
                .collect(Collectors.toList());
    }

    public City getCityById(Integer id) {

        CityEntity cityEntity = findCityById(id);

        return CityMapper.fromEntityWithTemperature(cityEntity, getCityCurrentTemperature(cityEntity));
    }

    public void createCity(City city) {

        validateCityNameIsUnique(city.getName(), city.getCountry());
        cityRepository.save(CityMapper.toEntity(city));
    }

    public void updateCity(Integer id, City city) {

        CityEntity cityEntity = findCityById(id);
        if (!city.getName().equals(cityEntity.getName()) || !city.getCountry().equals(cityEntity.getCountry())) {
            validateCityNameIsUnique(city.getName(), city.getCountry());
        }

        cityEntity.setName(city.getName());
        cityEntity.setCountry(city.getCountry());
        cityEntity.setStateOrRegion(city.getStateOrRegion());
        cityEntity.setPopulation(city.getPopulation());

        cityRepository.save(cityEntity);
    }

    public void deleteCity(Integer id) {

        CityEntity cityEntity = findCityById(id);
        cityRepository.delete(cityEntity);
    }

    private CityEntity findCityById(Integer id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }

    private void validateCityNameIsUnique(String name, String country) {
        if (cityRepository.existsByNameAndCountry(name, country)) {
            throw new CityAlreadyExistsException(name, country);
        }
    }

    private String getCityCurrentTemperature(CityEntity city) {
        return weatherApiService.getCityTemperature(city.getName());
    }
}
