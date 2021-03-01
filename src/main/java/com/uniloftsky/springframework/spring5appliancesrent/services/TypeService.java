package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Type;

import java.util.Comparator;
import java.util.Set;

public interface TypeService extends GenericService<Type, Long> {

    Set<Type> findAllSortedById(Comparator<Type> comparator);

}
