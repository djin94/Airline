package com.foxminded.airline.utils;

import com.foxminded.airline.domain.entity.FlightPrice;
import com.foxminded.airline.dto.FlightPriceDTO;

import java.util.ArrayList;
import java.util.List;

public class FlightPriceConverter {
    public List<FlightPriceDTO> createDTOsForFlightPrices(List<FlightPrice> flightPrices) {
        List<FlightPriceDTO> flightPriceDTOS = new ArrayList<>();
        flightPrices.forEach(flightPrice -> {
            FlightPriceDTO flightPriceDTO = new FlightPriceDTO();
            flightPriceDTO.setLevel(flightPrice.getLevel());
            flightPriceDTO.setPrice(String.valueOf((float) flightPrice.getPrice() / 100));
            flightPriceDTOS.add(flightPriceDTO);
        });
        return flightPriceDTOS;
    }
}
