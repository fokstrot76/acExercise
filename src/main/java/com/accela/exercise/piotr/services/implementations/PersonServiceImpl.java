package com.accela.exercise.piotr.services.implementations;

import com.accela.exercise.piotr.domains.Person;
import com.accela.exercise.piotr.services.JpaDaoService;
import com.accela.exercise.piotr.services.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class PersonServiceImpl extends JpaDaoService implements PersonService {

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Person", Person.class).getResultList();
    }

    @Override
    public Person getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Person.class, id);
    }

    @Override
    public Person saveOrUpdate(Person domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person savedPerson = em.merge(domainObject);
        em.getTransaction().commit();
        return savedPerson;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Person.class, id));
        em.getTransaction().commit();
    }
}
