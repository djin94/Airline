package com.foxminded.airline.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static HibernateUtils hibernateUtils;
    private SessionFactory sessionFactory;

    private HibernateUtils() {

    }

    private SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            shutdown();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void shutdown() {
        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static HibernateUtils getInstance() {
        if (hibernateUtils == null) {
            hibernateUtils = new HibernateUtils();
            hibernateUtils.sessionFactory = hibernateUtils.buildSessionFactory();
        }
        return hibernateUtils;
    }
}
