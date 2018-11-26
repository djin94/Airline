package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.TicketService;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.web.dto.TicketDTO;
import com.foxminded.airline.web.dto.UserDTO;
import com.foxminded.airline.domain.service.utils.TicketConverter;
import com.foxminded.airline.domain.service.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        if (userService.getCurrentUser().isPresent()) {
            user = userService.getCurrentUser().get();
        }
        return "user/userindex";
    }

    @GetMapping(value = "/api/v1/user/userlogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserName() {
        return new ResponseEntity<>(userConverter.createUserDTOFromUser(user), HttpStatus.OK);
    }

    @GetMapping(value = "/user/account")
    public String showAccountPage() {
        return "user/account";
    }

    @GetMapping(value = "/user/passenger")
    public String showEditPassengerPage() {
        return "user/passenger";
    }

    @PutMapping(value = "/api/v1/user/passenger")
    public ResponseEntity<String> editPassenger(@RequestBody UserDTO userDTO) {
        userService.editPassportData(user, userDTO);
        userService.save(user);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping(value = "/user/history")
    public String showHistoryPage() {
        return "user/history";
    }

    @GetMapping(value = "/api/v1/user/history/currenthistory")
    public ResponseEntity<List<TicketDTO>> getFlights() {
        return new ResponseEntity<>(ticketConverter.createTicketDTOsFromTickets(ticketService.findTicketsByUser(user)), HttpStatus.OK);
    }
}