package com.uniloftsky.springframework.spring5appliancesrent.comparators.user;

import com.uniloftsky.springframework.spring5appliancesrent.model.User;

import java.util.Comparator;

public class UserAscComparatorById implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
