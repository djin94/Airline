package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.FlightPrice;
import com.foxminded.airline.dto.FlightPriceDTO;

import java.util.List;

public interface FlightPriceService {
    List<FlightPriceDTO> createDTOsForFlightPrices(List<FlightPrice> flightPrices);
}
