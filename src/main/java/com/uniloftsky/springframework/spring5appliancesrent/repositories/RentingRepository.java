package com.uniloftsky.springframework.spring5appliancesrent.repositories;

import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentingRepository extends JpaRepository<Renting, Long> {
}
