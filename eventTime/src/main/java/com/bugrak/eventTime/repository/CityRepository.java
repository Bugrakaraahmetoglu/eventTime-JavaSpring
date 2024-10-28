package com.bugrak.eventTime.repository;

import com.bugrak.eventTime.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
}
