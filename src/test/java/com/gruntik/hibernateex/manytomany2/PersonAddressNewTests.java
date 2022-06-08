package com.gruntik.hibernateex.manytomany2;

import com.gruntik.hibernateex.entity.manytomanynew.AddressNew;
import com.gruntik.hibernateex.entity.manytomanynew.PersonNew;
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
public class PersonAddressNewTests {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    static CommonServices commonServices = new CommonServices();

    static {
        commonServices.setSession(session);
    }

    @Test
    void personAddress() {
        commonServices.saveEntity(new PersonNew(1L, "igor"));
        PersonNew person = commonServices.getByClassAndId(PersonNew.class, 1L);
        System.out.println("person one: " + person);

        List<PersonNew> persons = commonServices.findAllByClass(PersonNew.class);
        System.out.println(persons);
    }

    @Test
    void saveToManyToMany() {
        System.out.println("------------------------");
        AddressNew address = new AddressNew(1L, "vit", "210030");
        AddressNew address2 = new AddressNew(2L, "brest", "341060");
        commonServices.saveEntity(address);
        commonServices.saveEntity(address2);

        PersonNew igor = new PersonNew(1L, "igor");
        igor.addAddress(address);
        igor.addAddress(address2);
        commonServices.saveEntity(igor);

        List<PersonNew> persons = commonServices.findAllByClass(PersonNew.class);
        assertEquals(2, persons.get(0).getAddresses().size());
        System.out.println(persons.get(0).getAddresses());

//        PersonNew personFetch = persons.get(0);
//        personFetch.getAddresses().remove(address);
//        commonServices.saveEntity(personFetch);

        igor.removeAddress(address2);
        System.out.println("assresses: " + igor.getAddresses());

        List<PersonNew> personsFetch = commonServices.findAllByClass(PersonNew.class);
        assertEquals(1, personsFetch.get(0).getAddresses().size());
        System.out.println(personsFetch.get(0).getAddresses());

//        List<AddressNew> addresses = commonServices.findAllByClass(AddressNew.class);
//        AddressNew address1 = addresses.get(0);
////        address1.setPersons(Lists.newArrayList(igor));
//        commonServices.saveEntity(address1);

//        List<AddressNew> addressesNew = commonServices.findAllByClass(AddressNew.class);
//
//        assertEquals(1, addressesNew.get(0).getPersons().size());
//        System.out.println("persons: " + addresses.get(0).getPersons());

        System.out.println("------------------------");
    }
}
