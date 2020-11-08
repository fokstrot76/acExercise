package com.accela.exercise.piotr.services;

import org.springframework.stereotype.Component;

import java.util.List;


public interface CRUD<T> {
    List<?> listAll();

   T getById(Integer id);

   T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
