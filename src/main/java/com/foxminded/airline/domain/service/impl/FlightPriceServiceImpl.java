package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.domain.entity.FlightPrice;
import com.foxminded.airline.domain.service.FlightPriceService;
import com.foxminded.airline.dto.FlightPriceDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("flightPriceService")
public class FlightPriceServiceImpl implements FlightPriceService {
    @Override
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
