package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.ItemAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.ItemDescComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final Comparator<Item> comparatorAscById = new ItemAscComparatorById();
    private final Comparator<Item> comparatorDescById = new ItemDescComparatorById();

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Set<Item> findAll() {
        return new HashSet<>(itemRepository.findAll());
    }

    @Override
    public Set<Item> findAllActive() {
        return findAllSortedById(comparatorAscById).stream().filter(Item::isActive).collect(toCollection(() -> new TreeSet<>(comparatorAscById)));
    }

    @Override
    public Set<Item> findAllSortedById(Comparator<Item> comparator) {
        TreeSet<Item> sortedItems = new TreeSet<>(comparator);
        itemRepository.findAll().stream().iterator().forEachRemaining(sortedItems::add);
        return sortedItems;
    }

    @Override
    public Set<Item> getLastPosts() {
        return findAllSortedById(comparatorDescById).stream().limit(3).collect(toCollection(() -> new TreeSet<>(comparatorDescById)));
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> optional = itemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Expected item not found!");
        }
        return optional.get();
    }

    @Override
    public Item save(Item obj) {
        return itemRepository.save(obj);
    }

    @Override
    public List<Item> saveAll(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    @Override
    public void delete(Item obj) {
        itemRepository.delete(obj);
    }
}
