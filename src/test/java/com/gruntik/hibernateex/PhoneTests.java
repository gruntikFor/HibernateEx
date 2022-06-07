package com.gruntik.hibernateex;

import com.gruntik.hibernateex.entity.Person;
import com.gruntik.hibernateex.entity.Phone;
import com.gruntik.hibernateex.service.PersonService;
import com.gruntik.hibernateex.service.PhoneService;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@SpringBootTest
public class PhoneTests {

    @Autowired
    PersonService personService;

    @Autowired
    PhoneService phoneService;

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    EntityManagerFactory emf = session.getSessionFactory();
    EntityManager entityManager = emf.createEntityManager();

    @Test
    void savePersonAndPhone() {
        Person person = new Person("igor");
        Phone phone = new Phone("8145748");

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        entityManager.persist(person);

        phone.setPerson(person);
        entityManager.persist(phone);
        entityManager.flush();

        tx.commit();

        System.out.println("----createQuery");
        List<Phone> phones = session.createQuery("select ph from Phone ph").getResultList();

        System.out.println("----call phones---");
        System.out.println(phones.get(0).getPerson().getName());

    }

    @Test
    void save() {
        Phone phone = new Phone();
        phone.setNumber("+37529");
        phoneService.save(phone);

        List<Person> phones = phoneService.findAll();
        System.out.println(phones);
    }

    @Test
    void saveEntity() {
        Phone phone = new Phone();
        phone.setNumber("+710");
        phoneService.saveEntity(phone);

        List<Person> phones = phoneService.findAll();
        System.out.println(phones);
    }
}
