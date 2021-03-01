package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.RentingRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RentingServiceImpl implements RentingService {

    private final RentingRepository rentingRepository;

    public RentingServiceImpl(RentingRepository rentingRepository) {
        this.rentingRepository = rentingRepository;
    }

    @Override
    public Set<Renting> findAll() {
        return new HashSet<>(rentingRepository.findAll());
    }

    @Override
    public Renting findById(Long id) {
        Optional<Renting> optional = rentingRepository.findById(id);
        if(optional.isEmpty()) {
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
}
