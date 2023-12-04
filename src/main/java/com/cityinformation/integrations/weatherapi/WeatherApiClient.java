package com.cityinformation.integrations.weatherapi;

import com.cityinformation.integrations.weatherapi.dto.CityRealTimeInfo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.cityinformation.general.constants.Constants.BAD_REQUEST_MESSAGE_CONTENT;

@FeignClient(
        name = "weather-api-client",
        url = "http://api.weatherapi.com/v1/current.json"
)
public interface WeatherApiClient {

    @GetMapping
    @CircuitBreaker(name = "getCityRealTimeInformation", fallbackMethod = "getCityRealTimeInformationFallback")
    ResponseEntity<CityRealTimeInfo> getCityRealTimeInformation(@RequestParam String key, @RequestParam String q);

    default ResponseEntity<CityRealTimeInfo> getCityRealTimeInformationFallback(String key, String q, RuntimeException e) {

        if (e.getMessage().contains(BAD_REQUEST_MESSAGE_CONTENT)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);

    }

}
