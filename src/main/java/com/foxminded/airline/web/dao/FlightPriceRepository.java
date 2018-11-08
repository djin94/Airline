package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.FlightPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightPriceRepository extends CrudRepository<FlightPrice, Integer> {
    Optional<FlightPrice> findByFlightAndLevelTicket(Flight flight, String levelTicket);

    List<FlightPrice> findByFlight(Flight flight);
}
