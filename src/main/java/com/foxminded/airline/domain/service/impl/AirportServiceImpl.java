package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.dao.AirportDAO;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.service.AirportService;
import com.foxminded.airline.dto.AirportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service("airportService")
public class AirportServiceImpl implements AirportService {
    @Autowired
    DataSource dataSource;

    @Autowired
    AirportDAO airportDAO;

    @Transactional
    @Override
    public List<Airport> findAirportsByNamePart(String namePart) {
        return airportDAO.findByNameLikeIgnoreCase("%" + namePart + "%");
    }

    @Override
    public List<AirportDTO> createDTOsForAirports(List<Airport> airports) {
        List<AirportDTO> airportsDTO = new ArrayList<>();
        airports.forEach(airport -> {
            AirportDTO suitableAirportDTO = new AirportDTO();
            suitableAirportDTO.setName(airport.getName());
            airportsDTO.add(suitableAirportDTO);
        });
        return airportsDTO;
    }
}
