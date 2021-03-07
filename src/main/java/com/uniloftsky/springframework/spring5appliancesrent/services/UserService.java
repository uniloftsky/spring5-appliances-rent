package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface UserService extends GenericService<User, Long> {

    User findByEmail(String email);
    User findByLogin(String login);
    User changePassword(Authentication authentication, String password);
    Set<User> getPopularUsers();

}
