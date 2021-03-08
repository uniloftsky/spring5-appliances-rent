package com.uniloftsky.springframework.spring5appliancesrent.comparators.renting;

import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;

import java.util.Comparator;

public class RentingDescComparatorByDate implements Comparator<Renting> {


    //without 0 return because need to display items in any case
    @Override
    public int compare(Renting o1, Renting o2) {
        int cmp = (o2.getDate().getYear() - o1.getDate().getYear());
        if (cmp == 0) {
            cmp = (o2.getDate().getMonthValue() - o1.getDate().getMonthValue());
            if (cmp == 0) {
                cmp = (o2.getDate().getDayOfMonth() - o1.getDate().getDayOfMonth());
            }
            if(cmp == 0) {
                return 1;
            }
        }
        return cmp;
    }
}
