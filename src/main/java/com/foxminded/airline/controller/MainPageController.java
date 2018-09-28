package com.foxminded.airline.controller;

import com.foxminded.airline.dao.FlightDAO;
import com.foxminded.airline.domain.Flight;
import com.foxminded.airline.form.FlightForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    @RequestMapping(value = "/", produces =
            MediaType.TEXT_HTML_VALUE,
            method = RequestMethod.GET)
    public String show() {
        return "index";
    }

    @RequestMapping(value = "/", produces =
            MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public ResponseEntity<List<FlightForm>> showFlight() throws IOException {
        FlightDAO flightDAO = new FlightDAO();
        List<Flight> flights = flightDAO.getAll();
        List<FlightForm> flightForms = new ArrayList<>();
        flights.stream()
                .forEach(flight -> {
                    FlightForm flightForm = new FlightForm();
                    flightForm.setNumber(flight.getNumber());
                    flightForm.setPlaneName(flight.getPlane().getName());
                    flightForm.setDateString(flight.getDate().toLocalDate().toString());
                    flightForm.setTimeString(flight.getDate().toLocalTime().toString());
                    flightForm.setDepartureAirport(flight.getDepartureAirport().getName());
                    flightForm.setArrivalAirport(flight.getArrivalAirport().getName());
                    flightForms.add(flightForm);
                });
        return new ResponseEntity<List<FlightForm>>(flightForms, HttpStatus.OK);
    }
}
