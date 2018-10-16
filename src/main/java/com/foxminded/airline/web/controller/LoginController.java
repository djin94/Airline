package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.web.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

//    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    @GetMapping(value = "/login")
    public String authenticateUser() {
        return "login";
    }

    @GetMapping(value = "/registration",consumes = "text/plain")
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
        Optional<User> userExists = userRepository.findByLogin(login);
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
