package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.web.dao.PassengerRepository;
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

    @Autowired
    PassengerConverter passengerConverter;

    @Override
    public Passenger findOrCreatePassengerFromPassengerDTO(PassengerDTO passengerDTO) {
        return passengerRepository.findByPassportNumber(passengerDTO.getPassportNumber())
                .orElseGet(() -> passengerConverter.createPassengerFromPassengerDTO(passengerDTO));
    }
}
