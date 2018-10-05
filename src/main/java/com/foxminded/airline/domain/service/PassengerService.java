package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Passenger;
import com.foxminded.airline.dto.PassengerDTO;
import com.foxminded.airline.dto.TicketDTO;

public interface PassengerService {
    Passenger findOrCreatePassengerFromPassengerDTO(PassengerDTO passengerDTO);
}
