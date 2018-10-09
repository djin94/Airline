package com.foxminded.airline.utils;

import com.foxminded.airline.domain.entity.Passenger;
import com.foxminded.airline.dto.PassengerDTO;
import org.springframework.stereotype.Component;

@Component
public class PassengerConverter {
    public Passenger createPassengerFromPassengerDTO(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(passengerDTO.getFirstName());
        passenger.setLastName(passengerDTO.getLastName());
        passenger.setPatronym(passengerDTO.getPatronym());
        passenger.setPassportNumber(passengerDTO.getPassportNumber());
        return passenger;
    }
}
