package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Type;

import java.util.Set;

public interface TypeService {

    Set<Type> findAll();
    Type findById(Long id);
    Type save(Type obj);
    void delete(Type obj);

}
