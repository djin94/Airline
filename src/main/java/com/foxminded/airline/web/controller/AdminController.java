package com.foxminded.airline.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping(value = "/admin")
    public String showAdminPage() {
        return "admin/admin";
    }

    @GetMapping(value = "/admin/searchflights")
    public String showSearchFlights() {
        return "admin/searchFlights";
    }
}
