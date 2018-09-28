package com.foxminded.airline.controller;

import org.springframework.stereotype.Controller;

@Controller
public class FlightController {

//    @RequestMapping(value = "/flight", produces =
//            MediaType.TEXT_HTML_VALUE,
//            method = RequestMethod.GET)
//    public String show() {
//        return "flight";
//    }
//
//    @RequestMapping(value = "/flight", produces =
//            MediaType.APPLICATION_JSON_VALUE,
//            method = RequestMethod.GET)
//    public ResponseEntity<List<FlightDTO>> showFlight() throws IOException {
//        FlightDAO flightDAO = new FlightDAO();
//        List<Flight> flights = flightDAO.getAll();
//        List<FlightDTO> flightForms = new ArrayList<>();
//        flights.stream()
//                .forEach(flight -> {
//                    FlightDTO flightForm = new FlightDTO();
//                    flightForm.setNumber(flight.getNumber());
//                    flightForm.setPlaneName(flight.getPlane().getName());
//                    flightForm.setDateString(flight.getDate().toLocalDate().toString());
//                    flightForm.setTimeString(flight.getDate().toLocalTime().toString());
//                    flightForm.setDepartureAirport(flight.getDepartureAirport().getName());
//                    flightForm.setArrivalAirport(flight.getArrivalAirport().getName());
//
//                    flightForms.add(flightForm);
//                });
//        return new ResponseEntity<List<FlightDTO>>(flightForms, HttpStatus.OK);
//    }
}
