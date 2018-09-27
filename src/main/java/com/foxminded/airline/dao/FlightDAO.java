package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Flight;
import com.foxminded.airline.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FlightDAO implements Storage<Flight> {
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

    @Override
    public void save(Flight flight) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(flight);
        session.getTransaction().commit();
    }

    @Override
    public void update(Flight flight) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(flight);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Flight flight) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(flight);
        session.getTransaction().commit();
    }

    @Override
    public Flight findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Flight flight = (Flight) session.createQuery("from Flight where Flight.id=:id").iterate().next();
        session.getTransaction().commit();
        return flight;
    }

    @Override
    public List<Flight> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Flight> flights = session.createQuery("from Flight").list();
        session.getTransaction().commit();
        return flights;
    }
}
