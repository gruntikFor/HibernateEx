package com.gruntik.hibernateex.userorder;

import com.gruntik.hibernateex.entity.userorder.Order;
import com.gruntik.hibernateex.entity.userorder.User;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class UserOrderTests {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    EntityManagerFactory emf = session.getSessionFactory();
    EntityManager entityManager = emf.createEntityManager();

    @Test
    void lazy() {
        Transaction tx = session.getTransaction();
        tx.begin();

        User user = new User();
        user.setUserId(1L);
        user.setName("igor");
        session.save(user);

        Order order = new Order();
        order.setOrderId(1L);
        order.setTitle("magazine");
        order.setUser(user);
        session.save(order);

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setTitle("pineapple");
        order2.setUser(user);
        session.save(order2);

        user.setOrder(Set.of(order, order2));

        session.flush();
        tx.commit();

        Order order2Fetch = getOrderById(2L);
        removeEntityTx(order2Fetch);

        User user1 = getUserById(1L);
        System.out.println("order after remove: " + user1.getOrder());

        List<Order> orders = getOrders();

        System.out.println("--orders");
        System.out.println(orders);


    }

    private void saveEntityTx(Object entity) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(entity);
        tx.commit();
    }

    private void removeEntityTx(Object entity) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(entity);
        tx.commit();
    }

    private User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    private Order getOrderById(Long id) {
        return entityManager.find(Order.class, id);
    }

    private List<Order> getOrders() {
        return (List<Order>) entityManager.createQuery("select od from Order od").getResultList();
    }
}
