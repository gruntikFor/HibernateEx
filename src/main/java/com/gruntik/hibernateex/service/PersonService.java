package com.gruntik.hibernateex.service;

import com.gruntik.hibernateex.entity.Person;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    public void save(Person person) {
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(person);
        tx.commit();
    }

    public List<Person> findAll() {
        return session.createQuery("select p from Person p").getResultList();
    }

}
