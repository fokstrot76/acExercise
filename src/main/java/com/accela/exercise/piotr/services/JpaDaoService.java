package com.accela.exercise.piotr.services;


import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class JpaDaoService {

    protected EntityManagerFactory emf;
    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf){
        this.emf = emf;
    }
}
