package com.gruntik.hibernateex.util;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
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

        scanPackages(configuration, "com.gruntik.hibernateex.entity");

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

    private static void scanPackages(Configuration configuration, String pack) {
        List<Class<?>> classes = EntityScanner.scanPackages(pack).result();

        for (Class<?> annotatedClass : classes) {
            configuration.addAnnotatedClass(annotatedClass);
        }
    }

}
