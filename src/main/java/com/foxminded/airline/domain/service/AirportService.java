package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.dto.AirportDTO;

import java.util.List;

public interface AirportService {
    List<Airport> findAirportsByNamePart(String namePart);

    List<AirportDTO> createDTOsForAirports(List<Airport> airports);
}
