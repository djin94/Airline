package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registerUser() {
        return "registration";
    }

    @PostMapping(value = "/registration",
            params = {"login", "password", "email", "phone"})
    public String createNewUser(@RequestParam("login") String login,
                                @RequestParam("password") String password,
                                @RequestParam("email") String email,
                                @RequestParam("phone") String phone) {
        Optional<User> userExists = userService.findUserByLogin(login);
        if (userExists.isPresent()) {
            return "redirect:/registration";
        } else {
            User user = new User();
            user.setLogin(login);
            user.setPassword(userService.cryptPassword(password));
            user.setEmail(email);
            user.setPhone(phone);
            userService.save(user);
        }
        return "redirect:/";
    }
}
