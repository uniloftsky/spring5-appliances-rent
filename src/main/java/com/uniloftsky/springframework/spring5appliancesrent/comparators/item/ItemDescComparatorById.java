package com.uniloftsky.springframework.spring5appliancesrent.comparators.item;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;

import java.util.Comparator;

public class ItemDescComparatorById implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return o2.getId().compareTo(o1.getId());
    }

}
