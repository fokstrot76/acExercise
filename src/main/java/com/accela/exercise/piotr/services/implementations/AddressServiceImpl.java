package com.accela.exercise.piotr.services.implementations;

import com.accela.exercise.piotr.domains.Address;
import com.accela.exercise.piotr.domains.Person;
import com.accela.exercise.piotr.services.AddressService;
import com.accela.exercise.piotr.services.JpaDaoService;
import com.accela.exercise.piotr.services.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class AddressServiceImpl extends JpaDaoService implements AddressService {

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Address", Address.class).getResultList();
    }

    @Override
    public Address getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Address.class, id);
    }

    @Override
    public Address saveOrUpdate(Address domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Address savedAddress = em.merge(domainObject);
        em.getTransaction().commit();
        return savedAddress;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Address.class, id));
        em.getTransaction().commit();
    }
}
