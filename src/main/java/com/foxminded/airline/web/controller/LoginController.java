package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String getLoginPage() {
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
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> userExists = userService.findUserByLogin(login);
        if (userExists.isPresent()) {
//            modelAndView.setViewName("registration");
            return "redirect:/registration";
        } else {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);
            userService.save(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("userindex");
        }
        return "redirect:/";
    }
}
