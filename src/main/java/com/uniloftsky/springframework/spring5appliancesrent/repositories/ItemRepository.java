package com.uniloftsky.springframework.spring5appliancesrent.repositories;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
