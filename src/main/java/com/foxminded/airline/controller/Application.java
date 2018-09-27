package com.foxminded.airline.controller;

import com.foxminded.airline.dao.AirlineDAO;
import com.foxminded.airline.dao.TicketDAO;
import com.foxminded.airline.domain.Airline;
import com.foxminded.airline.domain.Ticket;
import com.foxminded.airline.utils.HibernateUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        System.out.println("Maven + Hibernate + PostgreSQL");
//        AirlineDAO airlineDAO = new AirlineDAO();
//        List<Airline> airlines = airlineDAO.getAll();
//        System.out.println("end");
//        HibernateUtils.getInstance().shutdown();
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String showTicket(){
        return "index";
    }
}
