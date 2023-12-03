package com.cityinformation.api.city;

import com.cityinformation.api.city.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
    boolean existsByNameAndCountry(String name, String country);
}
