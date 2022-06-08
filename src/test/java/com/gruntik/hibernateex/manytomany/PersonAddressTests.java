package com.gruntik.hibernateex.manytomany;

import com.gruntik.hibernateex.entity.manytomany.implicittable.Address;
import com.gruntik.hibernateex.entity.manytomany.implicittable.Person;
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
public class PersonAddressTests {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    static CommonServices commonServices = new CommonServices();

    static {
        commonServices.setSession(session);
    }

    @Test
    void personAddress() {
        commonServices.saveEntity(new Person(1L, "igor"));
        Person person = commonServices.getByClassAndId(Person.class, 1L);
        System.out.println("person one: " + person);

        List<Person> persons = commonServices.findAllByClass(Person.class);
        System.out.println(persons);
    }

    @Test
    void saveToManyToMany() {
        System.out.println("------------------------");
        Address address = new Address(1L, "vit", "210030");
        Address address2 = new Address(2L, "brest", "341060");
        commonServices.saveEntity(address);
        commonServices.saveEntity(address2);

        Person igor = new Person(1L, "igor");
        igor.setAddresses(Lists.newArrayList(address, address2));
        commonServices.saveEntity(igor);

        List<Person> persons = commonServices.findAllByClass(Person.class);
        assertEquals(2, persons.get(0).getAddresses().size());
        System.out.println(persons.get(0).getAddresses());

        Person personFetch = persons.get(0);
        personFetch.getAddresses().remove(address);
        commonServices.saveEntity(personFetch);

        List<Person> personsFetch = commonServices.findAllByClass(Person.class);
        assertEquals(1, personsFetch.get(0).getAddresses().size());
        System.out.println(personsFetch.get(0).getAddresses());

        List<Address> addresses = commonServices.findAllByClass(Address.class);
        Address address1 = addresses.get(0);
        address1.setPersons(Lists.newArrayList(igor));
        commonServices.saveEntity(address1);

        List<Address> addressesNew = commonServices.findAllByClass(Address.class);

        assertEquals(1, addressesNew.get(0).getPersons().size());
        System.out.println("persons: " + addresses.get(0).getPersons());

        System.out.println("------------------------");
    }
}
