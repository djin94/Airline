package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.dao.PassengerDAO;
import com.foxminded.airline.domain.entity.Passenger;
import com.foxminded.airline.domain.service.PassengerService;
import com.foxminded.airline.dto.PassengerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerDAO passengerDAO;

    @Override
    public Passenger findOrCreatePassengerFromPassengerDTO(PassengerDTO passengerDTO) {
        Passenger passenger;
        if (passengerDAO.findByPassportNumber(passengerDTO.getPassportNumber()).isPresent()) {
            passenger = passengerDAO.findByPassportNumber(passengerDTO.getPassportNumber()).get();
        } else {
            passenger = createPassengerFromPassengerDTO(passengerDTO);
            passengerDAO.save(passenger);
        }
        return passenger;
    }

    private Passenger createPassengerFromPassengerDTO(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(passengerDTO.getFirstName());
        passenger.setLastName(passengerDTO.getLastName());
        passenger.setPatronym(passengerDTO.getPatronym());
        passenger.setPassportNumber(passengerDTO.getPassportNumber());
        return passenger;
    }
}
