package com.foxminded.airline.utils;

import com.foxminded.airline.domain.entity.FlightPrice;
import com.foxminded.airline.dto.FlightPriceDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightPriceConverter {
    public List<FlightPriceDTO> createDTOsForFlightPrices(List<FlightPrice> flightPrices) {
        List<FlightPriceDTO> flightPriceDTOS = new ArrayList<>();
        flightPrices.forEach(flightPrice -> {
            FlightPriceDTO flightPriceDTO = new FlightPriceDTO();
            flightPriceDTO.setLevelTicket(flightPrice.getLevelTicket());
            flightPriceDTO.setPrice(String.valueOf((float) flightPrice.getPrice() / 100));
            flightPriceDTOS.add(flightPriceDTO);
        });
        return flightPriceDTOS;
    }
}
