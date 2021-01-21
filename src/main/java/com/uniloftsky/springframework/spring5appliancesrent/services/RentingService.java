package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;

import java.util.Set;

public interface RentingService {

    Set<Renting> findAll();
    Renting findById(Long id);
    Renting save(Renting obj);
    void delete(Renting obj);

}
