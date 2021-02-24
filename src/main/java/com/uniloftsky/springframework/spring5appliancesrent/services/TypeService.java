package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public interface TypeService {

    Set<Type> findAll();
    Set<Type> findAllSortedById(Comparator<Type> comparator);
    Type findById(Long id);
    Type save(Type obj);
    List<Type> saveAll(List<Type> types);
    void delete(Type obj);

}
