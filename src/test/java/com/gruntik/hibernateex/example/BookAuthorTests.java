package com.gruntik.hibernateex.example;

import com.gruntik.hibernateex.entity.example.Author;
import com.gruntik.hibernateex.entity.example.Book;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.DateFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@SpringBootTest
public class BookAuthorTests {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    EntityManagerFactory emf = session.getSessionFactory();
    EntityManager entityManager = emf.createEntityManager();

    @Test
    void lazy() throws ParseException {
        Transaction tx = session.getTransaction();
        tx.begin();

        Author martinFowler = new Author();
        martinFowler.setFullName("Martin Fowler");
        session.save(martinFowler);

        Book poeaa = new Book();
        poeaa.setIsbn("007-6092019909");
        poeaa.setTitle("Patterns of Enterprise Application Architecture");
        DateFormatter df = new DateFormatter();
        poeaa.setPublicationDate(df.parse("11.03.1999", Locale.GERMAN));
        poeaa.setAuthors(List.of(martinFowler));
        session.save(poeaa);

        tx.commit();

        System.out.println("-----find ");
        Book book1 = entityManager.find(Book.class, 1L);
        System.out.println(book1);
        System.out.println(book1.getAuthors());

    }
}
