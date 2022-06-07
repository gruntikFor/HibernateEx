package com.gruntik.hibernateex;

import com.gruntik.hibernateex.entity.Pet;
import com.gruntik.hibernateex.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PetTests {

    @Autowired
    PetService petService;

    @Test
    void savePerson() {
        petService.save();
        petService.printPet();
    }

    @Test
    void findAllPets() {
        petService.save();
        List<Pet> allPets = petService.findAllPets();
        System.out.println(allPets);

        System.out.println("--------------");

        List<Pet> allPetsCriteria = petService.findAllPetsCriteria();
        System.out.println(allPetsCriteria);

    }
}
