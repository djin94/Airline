package com.foxminded.airline.web.controller;

import com.foxminded.airline.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @GetMapping(value = "/user")
    public String showMainPage() {
        return "user/userindex";
    }

    @GetMapping(value = "/user/userlogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserName() {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("kool");
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            userDTO.setLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/user/account")
    public String showAccount() {
        return "user/account";
    }

    @GetMapping(value = "/user/history")
    public String showHistory(){
        return "user/history";
    }

    @GetMapping(value = "/user/passenger")
    public String showEditPassenger(){
        return "user/passenger";
    }
}
