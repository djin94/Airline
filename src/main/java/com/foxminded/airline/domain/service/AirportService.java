package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> searchAirportByNamePart(String namePart);
}
