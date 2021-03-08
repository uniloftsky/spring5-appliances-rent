package com.uniloftsky.springframework.spring5appliancesrent.comparators.renting;

import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;

import java.util.Comparator;

public class RentingDescComparatorByDate implements Comparator<Renting> {

    @Override
    public int compare(Renting o1, Renting o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
