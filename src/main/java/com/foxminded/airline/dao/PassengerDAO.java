package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Passenger;
import com.foxminded.airline.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PassengerDAO implements Storage<Passenger> {
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

    @Override
    public void save(Passenger passenger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(passenger);
        session.getTransaction().commit();
    }

    @Override
    public void update(Passenger passenger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(passenger);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Passenger passenger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(passenger);
        session.getTransaction().commit();
    }

    @Override
    public Passenger findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Passenger passenger = (Passenger) session.createQuery("from Passenger where Passenger.id=:id").iterate().next();
        session.getTransaction().commit();
        return passenger;
    }

    @Override
    public List<Passenger> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Passenger> passengers = session.createQuery("from Passenger").list();
        session.getTransaction().commit();
        return passengers;
    }
}
