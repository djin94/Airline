package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Plane;
import com.foxminded.airline.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PlaneDAO implements Storage<Plane> {
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

    @Override
    public void save(Plane plane) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(plane);
        session.getTransaction().commit();
    }

    @Override
    public void update(Plane plane) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(plane);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Plane plane) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(plane);
        session.getTransaction().commit();
    }

    @Override
    public Plane findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Plane plane = (Plane) session.createQuery("from Plane where Plane.id=:id").iterate().next();
        session.getTransaction().commit();
        return plane;
    }

    @Override
    public List<Plane> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Plane> planes = session.createQuery("from Plane").list();
        session.getTransaction().commit();
        return planes;
    }
}
