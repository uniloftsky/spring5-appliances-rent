package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import org.springframework.security.core.Authentication;

public interface UserService extends GenericService<User, Long> {

    User findByEmail(String email);
    User findByLogin(String login);
    User changePassword(Authentication authentication, String password);

}
