package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String showUserRegistrationPage() {
        return "registration";
    }

    @PostMapping(value = "/registration",
            params = {"login", "password", "email", "phone"})
    public String createNewUser(@RequestParam("login") String login,
                                @RequestParam("password") String password,
                                @RequestParam("email") String email,
                                @RequestParam("phone") String phone) {
        if (userService.createUser(login, password, email, phone)) {
            return "redirect:/";
        } else {
            return "redirect:/registration";
        }
    }
}
