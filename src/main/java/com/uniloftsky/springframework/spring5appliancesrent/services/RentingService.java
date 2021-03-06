package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;

import java.util.Comparator;
import java.util.TreeSet;

public interface RentingService extends GenericService<Renting, Long> {

    Renting saveOrder(User owner, Item item);
    TreeSet<Renting> findAllSortedById(Comparator<Renting> comparator);

}
