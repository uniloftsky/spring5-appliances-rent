package com.uniloftsky.springframework.spring5appliancesrent.comparators.user;

import com.uniloftsky.springframework.spring5appliancesrent.model.User;

import java.util.Comparator;

public class UserDescComparatorByItemsCount implements Comparator<User> {

    //without 0 return because need to display items in any case
    @Override
    public int compare(User o1, User o2) {
        return (o2.getItems().size() < o1.getItems().size()) ? -1 : ((o2.getItems().size() == o1.getItems().size()) ? 1 : 1);
    }
}
