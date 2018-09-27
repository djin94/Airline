package com.foxminded.airline.controller;

import com.foxminded.airline.dao.AirlineDAO;
import com.foxminded.airline.domain.Airline;
import com.foxminded.airline.utils.HibernateUtils;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + PostgreSQL");
        AirlineDAO airlineDAO = new AirlineDAO();
        List<Airline> airlines = airlineDAO.getAll();
        System.out.println("end");
        HibernateUtils.getInstance().shutdown();
    }
}
