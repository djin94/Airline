package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.service.AirportService;
import com.foxminded.airline.dao.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Airport> findAirportsByNamePart(String namePart) {
        return airportRepository.findByNameLikeIgnoreCase("%" + namePart + "%");
    }
}
