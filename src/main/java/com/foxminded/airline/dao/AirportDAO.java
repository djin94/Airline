package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Airport;;
import com.foxminded.airline.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AirportDAO implements Storage<Airport> {
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

    @Override
    public void save(Airport airport) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(airport);
        session.getTransaction().commit();
    }

    @Override
    public void update(Airport airport) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(airport);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Airport airport) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(airport);
        session.getTransaction().commit();
    }

    @Override
    public Airport findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Airport airport = (Airport) session.createQuery("from Airport where Airport.id=:id").iterate().next();
        session.getTransaction().commit();
        return airport;
    }

    @Override
    public List<Airport> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Airport> airports = (List<Airport>) session.createQuery("from Airport").list();
        session.getTransaction().commit();
        return airports;
    }
}
