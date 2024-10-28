package com.bugrak.eventTime.service;

import com.bugrak.eventTime.model.City;
import com.bugrak.eventTime.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City findById(Integer id) {
        return cityRepository.findById(id).orElse(null);
    }
}
