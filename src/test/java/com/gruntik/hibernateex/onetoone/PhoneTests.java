package com.gruntik.hibernateex.onetoone;

import com.gruntik.hibernateex.entity.onetoone.Phone;
import com.gruntik.hibernateex.entity.onetoone.PhoneDetail;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PhoneTests {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    @Test
    void phoneDetails() {
        System.out.println("------------");

        Phone iPhone = new Phone(1L, "iPhone");
        saveEntity(iPhone);

        PhoneDetail phoneDetail = new PhoneDetail(1L, "MTC", "5G");
        saveEntity(phoneDetail);

        assertEquals(findAllPhones().get(0), iPhone);
        assertEquals(findAllPhoneDetails().get(0), phoneDetail);
        System.out.println("------------");
    }

    public void saveEntity(Object object) {
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(object);
        tx.commit();
    }

    public List<Phone> findAllPhones() {
        return session.createQuery("select p from Phone p").getResultList();
    }

    public List<PhoneDetail> findAllPhoneDetails() {
        return session.createQuery("select pd from PhoneDetail pd").getResultList();
    }

}
