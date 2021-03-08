package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.item.ItemDescComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.renting.RentingDescComparatorByDate;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.user.UserDescComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.user.UserDescComparatorByItemsCount;
import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toCollection;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Comparator<User> comparatorDescById = new UserDescComparatorById();
    private final Comparator<User> comparatorDescByItemsCount = new UserDescComparatorByItemsCount();
    private final Comparator<Item> itemComparatorDescById = new ItemDescComparatorById();
    private final Comparator<Renting> rentingComparatorDescByDate = new RentingDescComparatorByDate();

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
    public TreeSet<Item> getUserItems(User user) {
        return user.getItems().stream().filter(Item::isActive).collect(toCollection(() -> new TreeSet<>(itemComparatorDescById)));
    }

    @Override
    public TreeSet<Renting> getUserRentings(User user) {
        return user.getRentings().stream().collect(toCollection(() -> new TreeSet<>(rentingComparatorDescByDate)));
    }

    @Override
    public Set<User> getPopularUsers() {
        TreeSet<User> popularUsers = new TreeSet<>(comparatorDescByItemsCount);
        userRepository.findAll().stream().iterator().forEachRemaining(popularUsers::add);
        return popularUsers.stream().limit(4)
                .collect(toCollection(() -> new TreeSet<>(comparatorDescByItemsCount)));
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
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(obj.getPassword());
            obj.setPassword(encodedPassword);
            obj.setRegisterDate(LocalDate.now());
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
