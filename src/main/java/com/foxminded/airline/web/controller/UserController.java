package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.dto.FlightDTO;
import com.foxminded.airline.dto.TicketDTO;
import com.foxminded.airline.dto.UserDTO;
import com.foxminded.airline.utils.TicketConverter;
import com.foxminded.airline.utils.UserConverter;
import com.foxminded.airline.web.dao.TicketRepository;
import com.foxminded.airline.web.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Autowired
    UserService userService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketConverter ticketConverter;

    UserDTO userDTO;

    User user;

    @GetMapping(value = "/user")
    public String showMainPage() {
        return "user/userindex";
    }

    @GetMapping(value = "/user/userlogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserName() {
        userDTO = new UserDTO();
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
        userRepository.save(user);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping(value = "/user/history")
    public String showHistory() {
        return "user/history";
    }

    @GetMapping(value = "/user/history/currenthistory")
    public ResponseEntity<List<TicketDTO>> getFlights() {
        return new ResponseEntity<>(ticketConverter.createTicketDTOsFromTickets(ticketRepository.findByUser(user)),HttpStatus.OK);
    }
}
