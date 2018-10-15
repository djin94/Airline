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

    @GetMapping(value = "/user",produces = {"text/plain","application/*"},headers={"Accept=text/plain"})
    public String showMainPage() {
        return "user/userindex";
    }

    @GetMapping(name = "/userlogin", produces = MediaType.APPLICATION_JSON_VALUE,headers="content-type=application/json")
    @ResponseBody
    public ResponseEntity<UserDTO> getUserName() {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("kool");
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            userDTO.setLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
