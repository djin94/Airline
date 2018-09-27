package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Airline;
import com.foxminded.airline.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AirlineDAO implements Storage<Airline> {
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

    @Override
    public void save(Airline airline) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(airline);
        session.getTransaction().commit();
    }

    @Override
    public void update(Airline airline) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(airline);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Airline airline) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(airline);
        session.getTransaction().commit();
    }

    @Override
    public Airline findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Airline airline = (Airline) session.createQuery("from Airline where Airline.id=:id").iterate().next();
        session.getTransaction().commit();
        return airline;
    }

    @Override
    public List<Airline> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Airline> airlines = session.createQuery("from Airline").list();
        session.getTransaction().commit();
        return airlines;
    }
}
