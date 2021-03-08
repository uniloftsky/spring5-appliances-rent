package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import org.springframework.security.core.Authentication;

import java.util.Set;
import java.util.TreeSet;

public interface UserService extends GenericService<User, Long> {

    User findByEmail(String email);
    User findByLogin(String login);
    User changePassword(Authentication authentication, String password);
    TreeSet<Item> getUserItems(User user);
    TreeSet<Renting> getUserRentings(User user);
    Set<User> getPopularUsers();

}
