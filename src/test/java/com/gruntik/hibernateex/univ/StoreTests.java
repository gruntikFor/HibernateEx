package com.gruntik.hibernateex.univ;

import com.gruntik.hibernateex.entity.univ.Ps5;
import com.gruntik.hibernateex.entity.univ.Store;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StoreTests {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Test
    void saveAndFind() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();

        Store store = new Store();
        store.setId(1L);
        store.setTitle("magazin");
        session.save(store);

        tx.commit();

        List<Store> stores = session.createQuery("from Store ").list();
        System.out.println(stores);

    }

    @Test
    void saveAndFindPs5() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();

        Ps5 ps = new Ps5(1L, 9999);
        session.save(ps);

        Store store = new Store(1L, "magazin");
        session.save(store);

        store.setPs5(ps);

        tx.commit();

        List<Store> stores = session.createQuery("from Store ").list();
        System.out.println(stores);
        System.out.println(stores.get(0).getPs5());

//        List<Ps5> pss = session.createQuery("from Ps5 ").list();
//        System.out.println(pss);

    }


}
