package com.foxminded.airline.dao;

import com.foxminded.airline.domain.FlightPrice;
import com.foxminded.airline.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FlightPriceDAO implements Storage<FlightPrice> {
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

    @Override
    public void save(FlightPrice flightPrice) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(flightPrice);
        session.getTransaction().commit();
    }

    @Override
    public void update(FlightPrice flightPrice) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(flightPrice);
        session.getTransaction().commit();
    }

    @Override
    public void delete(FlightPrice flightPrice) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(flightPrice);
        session.getTransaction().commit();
    }

    @Override
    public FlightPrice findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        FlightPrice flightPrice = (FlightPrice) session.createQuery("from FlightPrice where FlightPrice.id=:id").iterate().next();
        session.getTransaction().commit();
        return flightPrice;
    }

    @Override
    public List<FlightPrice> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<FlightPrice> flightPrices = session.createQuery("from FlightPrice").list();
        session.getTransaction().commit();
        return flightPrices;
    }
}
