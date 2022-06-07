package com.gruntik.hibernateex;

import com.gruntik.hibernateex.entity.Person;
import com.gruntik.hibernateex.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonTests {

    @Autowired
    PersonService personService;

    @Test
    void findAll() {
        Person person = new Person();
        person.setName("sasah");

        Person person2 = new Person();
        person2.setName("igor");

        personService.save(person);
        personService.save(person2);

        personService.findAll().forEach(System.out::println);
    }
}
