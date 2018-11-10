package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.TicketService;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.dto.TicketDTO;
import com.foxminded.airline.dto.UserDTO;
import com.foxminded.airline.utils.TicketConverter;
import com.foxminded.airline.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketConverter ticketConverter;

    private User user;

    @GetMapping(value = "/user")
    public String showMainPage() {
        return "user/userindex";
    }

    @GetMapping(value = "/user/userlogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserName() {
        UserDTO userDTO = new UserDTO();
        user = userService.getCurrentUser();
        userDTO.setLogin(user.getLogin());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/user/account")
    public String showAccount() {
        return "user/account";
    }

    @GetMapping(value = "/user/passenger")
    public String showEditPassenger() {
        return "user/passenger";
    }

    @GetMapping(value = "/user/passenger/currentdata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getPassengerData() {
        return new ResponseEntity<>(userConverter.createUserDTOFromUser(user), HttpStatus.OK);
    }

    @PostMapping(value = "/user/passenger/edit")
    public ResponseEntity<String> editPassenger(@RequestBody UserDTO userDTO) {
        userService.editPassportData(user, userDTO);
        userService.save(user);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping(value = "/user/history")
    public String showHistory() {
        return "user/history";
    }

    @GetMapping(value = "/user/history/currenthistory")
    public ResponseEntity<List<TicketDTO>> getFlights() {
        return new ResponseEntity<>(ticketConverter.createTicketDTOsFromTickets(ticketService.findTicketsByUser(user)), HttpStatus.OK);
    }
}