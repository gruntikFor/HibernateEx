package com.gruntik.hibernateex.service;

import com.gruntik.hibernateex.entity.Person;
import com.gruntik.hibernateex.entity.Phone;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PhoneService {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    EntityManagerFactory emf = session.getSessionFactory();
    EntityManager entityManager = emf.createEntityManager();

    public void save(Phone phone) {
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(phone);
        tx.commit();
    }

    public void saveEntity(Phone phone) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(phone);
        transaction.commit();
    }

    public List<Person> findAll() {
        return session.createQuery("select p from Phone p").getResultList();
    }

}
