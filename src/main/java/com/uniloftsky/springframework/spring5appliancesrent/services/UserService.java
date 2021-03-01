package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.User;

public interface UserService extends GenericService<User, Long> {

    User findByEmail(String email);
    User findByLogin(String login);

}
