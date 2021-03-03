package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> findAll() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public User findById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Expected user not found!");
        }
        return optional.get();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User changePassword(Authentication authentication, String password) {
        User user = userRepository.findByLogin(authentication.getName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String newPassword = encoder.encode(password);
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    @Override
    public User save(User obj) {
        if (obj.getId() != null) {
            User foundUser = findById(obj.getId());
            foundUser.setFirstName(obj.getFirstName());
            foundUser.setLastName(obj.getLastName());
            foundUser.setEmail(obj.getEmail());
            foundUser.setPhone(obj.getPhone());
            foundUser.setLogin(obj.getLogin());
            return userRepository.save(foundUser);
        } else {
            return userRepository.save(obj);
        }

    }

    @Override
    public List<User> saveAll(List<User> list) {
        return userRepository.saveAll(list);
    }

    @Override
    public void delete(User obj) {
        userRepository.delete(obj);
    }
}
