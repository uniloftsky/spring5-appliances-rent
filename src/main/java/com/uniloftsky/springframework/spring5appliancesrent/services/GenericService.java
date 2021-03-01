package com.uniloftsky.springframework.spring5appliancesrent.services;

import java.util.List;
import java.util.Set;

public interface GenericService<T, ID> {

    Set<T> findAll();
    T findById(ID id);
    T save(T obj);
    List<T> saveAll(List<T> list);
    void delete(T obj);

}
