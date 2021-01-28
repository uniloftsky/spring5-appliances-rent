package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.User;

import java.util.Set;

public interface UserService {

    Set<User> findAll();
    User findById(Long id);
    User findByEmail(String email);
    User findByLogin(String login);
    User save(User obj);
    void delete(User obj);

}
