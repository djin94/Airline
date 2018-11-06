package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.service.impl.FlightServiceImpl;
import com.foxminded.airline.utils.FlightConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    FlightServiceImpl flightService;

    @Autowired
    FlightConverter flightConverter;

    @GetMapping(value = "/admin")
    public String showAdminPage(){
        return "admin/admin";
    }

    @GetMapping(value = "/admin/searchflights")
    public String showSearchFlights(){
        return "admin/searchFlights";
    }
}
