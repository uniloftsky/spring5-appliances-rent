package com.uniloftsky.springframework.spring5appliancesrent.comparators.renting;

import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;

import java.util.Comparator;

public class RentingAscComparatorById implements Comparator<Renting> {

    @Override
    public int compare(Renting o1, Renting o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
