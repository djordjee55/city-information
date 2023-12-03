package com.cityinformation.api.city;

import com.cityinformation.api.city.dto.CityResponse;
import com.cityinformation.api.city.dto.CreateCityRequest;
import com.cityinformation.api.city.dto.UpdateCityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/city")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityResponse> getAllCities() {
        return cityService.getAllCities().stream().map(CityMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public CityResponse getCityById(@PathVariable Integer id) {
        return CityMapper.toResponse(cityService.getCityById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCity(@RequestBody @Validated CreateCityRequest createCityRequest) {
        cityService.createCity(CityMapper.fromCreateRequest(createCityRequest));
    }

    @PutMapping(value = "/{id}")
    public void updateCity(@RequestBody @Validated UpdateCityRequest updateCityRequest, @PathVariable Integer id) {
        cityService.updateCity(id, CityMapper.fromUpdateRequest(updateCityRequest));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Integer id) {
        cityService.deleteCity(id);
    }
}
