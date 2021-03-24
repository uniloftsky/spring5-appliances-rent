package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.RentingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class RentingServiceImpl implements RentingService {

    private final RentingRepository rentingRepository;
    private final ItemService itemService;
    private final UserService userService;

    public RentingServiceImpl(RentingRepository rentingRepository, ItemService itemService, UserService userService) {
        this.rentingRepository = rentingRepository;
        this.itemService = itemService;
        this.userService = userService;
    }

    @Override
    public Set<Renting> findAll() {
        return new HashSet<>(rentingRepository.findAll());
    }

    @Override
    public Renting findById(Long id) {
        Optional<Renting> optional = rentingRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Expected renting not found!");
        }
        return optional.get();
    }

    @Override
    public Renting save(Renting obj) {
        return rentingRepository.save(obj);
    }

    @Override
    public List<Renting> saveAll(List<Renting> list) {
        return rentingRepository.saveAll(list);
    }

    @Override
    public void delete(Renting obj) {
        rentingRepository.delete(obj);
    }

    @Override
    public Renting saveOrder(User owner, Item item) {
        if (item.isActive()) {
            Renting renting = new Renting(item, item.getPrice(), LocalDate.now());
            item.setActive(false);
            itemService.save(item);
            owner.getRentings().add(renting);
            userService.save(owner);
            return rentingRepository.save(renting);
        } else {
            throw new RuntimeException("Ви намагаєтесь орендувати неактивне оголошення!");
        }
    }

    @Override
    public TreeSet<Renting> findAllSortedById(Comparator<Renting> comparator) {
        TreeSet<Renting> sortedRentings = new TreeSet<>(comparator);
        rentingRepository.findAll().stream().iterator().forEachRemaining(sortedRentings::add);
        return sortedRentings;
    }
}
