package com.foxminded.airline.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;


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
}
