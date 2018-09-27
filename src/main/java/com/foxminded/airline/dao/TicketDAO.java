package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Ticket;
import com.foxminded.airline.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TicketDAO implements Storage<Ticket> {
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

    @Override
    public void save(Ticket ticket) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ticket);
        session.getTransaction().commit();
    }

    @Override
    public void update(Ticket ticket) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(ticket);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Ticket ticket) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(ticket);
        session.getTransaction().commit();
    }

    @Override
    public Ticket findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ticket ticket = (Ticket) session.createQuery("from Ticket where Ticket.id=:id").list().get(0);
        session.getTransaction().commit();
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Ticket> tickets = session.createQuery("from Ticket").list();
        session.getTransaction().commit();
        return tickets;
    }
}
