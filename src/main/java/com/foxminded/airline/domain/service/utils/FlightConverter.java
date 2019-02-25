package com.foxminded.airline.domain.service.utils;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.web.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlightConverter {

    @Autowired
    private FlightService flightService;

    public List<FlightDTO> createDTOsForFlights(List<Flight> flights) {
        List<FlightDTO> flightDTOS = new ArrayList<>();
        flights.forEach(flight -> {
            FlightDTO flightDTO = createFlightDTOFromFlight(flight);
            flightDTOS.add(flightDTO);
        });
        return flightDTOS;
    }

    public FlightDTO createFlightDTOFromFlight(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setNumber(flight.getNumber());
        flightDTO.setPlaneName(flight.getPlane().getName());
        flightDTO.setDateString(flight.getDate().toString());
        flightDTO.setTimeString(flight.getTime().toString());
        flightDTO.setDepartureAirport(flight.getDepartureAirport().getName());
        flightDTO.setArrivalAirport(flight.getArrivalAirport().getName());
        flightDTO.setEnabled(flightService.isFlightEnabled(flight));
        return flightDTO;
    }
}
