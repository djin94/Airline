package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.dao.PassengerRepository;
import com.foxminded.airline.domain.entity.Passenger;
import com.foxminded.airline.domain.service.PassengerService;
import com.foxminded.airline.dto.PassengerDTO;
import com.foxminded.airline.utils.PassengerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public Passenger findOrCreatePassengerFromPassengerDTO(PassengerDTO passengerDTO) {
        Passenger passenger;
        if (passengerRepository.findByPassportNumber(passengerDTO.getPassportNumber()).isPresent()) {
            passenger = passengerRepository.findByPassportNumber(passengerDTO.getPassportNumber()).get();
        } else {
            passenger = new PassengerConverter().createPassengerFromPassengerDTO(passengerDTO);
            passengerRepository.save(passenger);
        }
        return passenger;
    }
}
