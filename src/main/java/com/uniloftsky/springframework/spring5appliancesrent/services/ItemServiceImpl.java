package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Set<Item> findAll() {
        return new HashSet<>(itemRepository.findAll());
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> optional = itemRepository.findById(id);
        if(optional.isEmpty()) {
            throw new RuntimeException("Expected item not found!");
        }
        return optional.get();
    }

    @Override
    public Item save(Item obj) {
        return itemRepository.save(obj);
    }

    @Override
    public void delete(Item obj) {
        itemRepository.delete(obj);
    }
}
