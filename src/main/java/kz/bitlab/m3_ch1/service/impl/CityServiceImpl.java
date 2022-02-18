package kz.bitlab.m3_ch1.service.impl;


import kz.bitlab.m3_ch1.entities.City;
import kz.bitlab.m3_ch1.repository.CityRepository;
import kz.bitlab.m3_ch1.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
