package com.gruntik.hibernateex.service;

import com.gruntik.hibernateex.entity.Pet;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class PetService {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    public void save() {
        Transaction tx = session.getTransaction();
        tx.begin();

        Pet pet = new Pet(1L, "asya", 12);
        Pet pet2 = new Pet(2L, "sem", 15);

        session.save(pet);
        session.save(pet2);

        tx.commit();
    }

    public void printPet() {
        Pet pet = session.load(Pet.class, 1L);
        System.out.println(pet);
    }

    public void printAllPets() {
        Pet pet = session.load(Pet.class, 1L);
    }

    public List<Pet> findAllPets() {
        return session.createQuery("select p from Pet p").getResultList();
    }

    public List<Pet> findAllPetsCriteria() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Pet> cq = cb.createQuery(Pet.class); //for clause
        Root<Pet> root = cq.from(Pet.class); //for get fields

        cq.where(cb.equal(root.get("name"), "asya"));

        CriteriaQuery<Pet> all = cq.select(root);

        Query<Pet> query = session.createQuery(all);
        return query.getResultList();
    }

}
