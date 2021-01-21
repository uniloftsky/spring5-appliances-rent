package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;

import java.util.Set;

public interface ItemService {

    Set<Item> findAll();
    Item findById(Long id);
    Item save(Item obj);
    void delete(Item obj);

}
