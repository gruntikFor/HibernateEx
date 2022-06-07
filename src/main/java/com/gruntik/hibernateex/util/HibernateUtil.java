package com.gruntik.hibernateex.util;

import com.gruntik.hibernateex.entity.*;
import com.gruntik.hibernateex.entity.example.Author;
import com.gruntik.hibernateex.entity.example.Book;
import com.gruntik.hibernateex.entity.univ.Ps5;
import com.gruntik.hibernateex.entity.univ.Store;
import com.gruntik.hibernateex.entity.userorder.Order;
import com.gruntik.hibernateex.entity.userorder.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.DRIVER, "org.h2.Driver");
        settings.put(Environment.URL, "jdbc:h2:mem:db:DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        settings.put(Environment.USER, "sa");
        settings.put(Environment.PASS, "sa");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Pet.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Phone.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(Store.class);
        configuration.addAnnotatedClass(Ps5.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Author.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

}
