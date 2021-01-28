package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public interface ItemService {

    Set<Item> findAll();
    Set<Item> findAllActive();
    Set<Item> findAllSortedById(Comparator<Item> comparator);
    Set<Item> getLastPosts();
    Page<Item> findAll(Pageable pageable);
    Item findById(Long id);
    Item save(Item obj);
    List<Item> saveAll(List<Item> items);
    void delete(Item obj);

}
