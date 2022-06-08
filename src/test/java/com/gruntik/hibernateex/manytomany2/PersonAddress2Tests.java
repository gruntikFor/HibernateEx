package com.gruntik.hibernateex.manytomany2;

import com.gruntik.hibernateex.entity.manytomany.Address;
import com.gruntik.hibernateex.entity.manytomany.Person;
import com.gruntik.hibernateex.entity.manytomany2.Address2;
import com.gruntik.hibernateex.entity.manytomany2.Person2;
import com.gruntik.hibernateex.service.CommonServices;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.assertj.core.util.Lists;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonAddress2Tests {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    static CommonServices commonServices = new CommonServices();

    static {
        commonServices.setSession(session);
    }

    @Test
    void personAddress() {
        commonServices.saveEntity(new Person2(1L, "igor"));
        Person2 person = commonServices.getByClassAndId(Person2.class, 1L);
        System.out.println("person one: " + person);

        List<Person2> persons = commonServices.findAllByClass(Person2.class);
        System.out.println(persons);
    }

    @Test
    void saveToManyToMany() {
        System.out.println("------------------------");
        Address2 address = new Address2(1L, "vit", "210030");
        Address2 address2 = new Address2(2L, "brest", "341060");
        commonServices.saveEntity(address);
        commonServices.saveEntity(address2);

        Person2 igor = new Person2(1L, "igor");
        igor.setAddresses(Lists.newArrayList(address, address2));
        commonServices.saveEntity(igor);

        List<Person2> persons = commonServices.findAllByClass(Person2.class);
        assertEquals(2, persons.get(0).getAddresses().size());
        System.out.println(persons.get(0).getAddresses());

        Person2 personFetch = persons.get(0);
        personFetch.getAddresses().remove(address);
        commonServices.saveEntity(personFetch);

        List<Person2> personsFetch = commonServices.findAllByClass(Person2.class);
        assertEquals(1, personsFetch.get(0).getAddresses().size());
        System.out.println(personsFetch.get(0).getAddresses());

        List<Address2> addresses = commonServices.findAllByClass(Address2.class);
        Address2 address1 = addresses.get(0);
        address1.setPersons(Lists.newArrayList(igor));
        commonServices.saveEntity(address1);

        List<Address2> addressesNew = commonServices.findAllByClass(Address2.class);

        assertEquals(1, addressesNew.get(0).getPersons().size());
        System.out.println("persons: " + addresses.get(0).getPersons());

        System.out.println("------------------------");
    }
}
