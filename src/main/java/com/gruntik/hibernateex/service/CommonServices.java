package com.gruntik.hibernateex.service;

import com.gruntik.hibernateex.entity.onetoone.Phone;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServices {

    private static Session session;

    public void setSession(Session session) {
        CommonServices.session = session;
    }

    public void saveEntity(Object object) {
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(object);
        tx.commit();
    }

    public <T> T getByClassAndId(Class<T> clazz, Long id) {
        Transaction tx = session.getTransaction();
        tx.begin();
        T object = session.load(clazz, id);
        tx.commit();
        return object;
    }

    public <T> List<T> findAllByClass(Class<T> clazz) {
        return session.createQuery("select o from " + clazz.getName() + " o").getResultList();
    }
}
