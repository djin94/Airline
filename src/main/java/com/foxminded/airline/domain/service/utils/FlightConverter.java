package com.foxminded.airline.domain.service.utils;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.web.dto.FlightDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlightConverter {
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
        flightDTO.setEnabled(isFlightEnabled(flight));
        return flightDTO;
    }

    private boolean isFlightEnabled(Flight flight) {
        int compareDate = flight.getDate().compareTo(LocalDate.now());
        if (compareDate < 0) {
            return false;
        }
        if (compareDate == 0) {
            int closingSaleTime = 2;
            int differentHours = flight.getTime().getHour() - LocalTime.now().getHour();
            if (differentHours > closingSaleTime) {
                return true;
            }
        }
        return true;
    }
}
