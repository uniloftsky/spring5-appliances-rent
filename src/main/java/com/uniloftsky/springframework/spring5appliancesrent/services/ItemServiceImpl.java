package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.ItemAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.ItemDescComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemPage;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemSearchCriteria;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.ItemRepository;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.item.ItemCriteriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toCollection;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final Comparator<Item> comparatorAscById = new ItemAscComparatorById();
    private final Comparator<Item> comparatorDescById = new ItemDescComparatorById();
    private final UserService userService;
    private final ItemCriteriaRepository itemCriteriaRepository;

    public ItemServiceImpl(ItemRepository itemRepository, UserService userService, ItemCriteriaRepository itemCriteriaRepository) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.itemCriteriaRepository = itemCriteriaRepository;
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
    public Set<Item> getLimitedCountPosts(Comparator<Item> comparator, int count) {
        return findAllSortedById(comparator).stream().limit(count).collect(toCollection(() -> new TreeSet<>(comparator)));
    }

    @Override
    public Set<Item> getLastPostsIndexPage() {
        return getLimitedCountPosts(comparatorDescById, 4)
                .stream()
                    .collect(toCollection(() -> new TreeSet<>(comparatorAscById)))
                .stream()
                    .limit(3).collect(toCollection(() -> new TreeSet<>(comparatorDescById)));
    }

    @Override
    public Set<Item> getSimilarPosts() {
        return getLimitedCountPosts(comparatorDescById, 4);
    }

    @Override
    public Page<Item> getCatalogItems(ItemPage itemPage, ItemSearchCriteria itemSearchCriteria) {
        return itemCriteriaRepository.findAllWithFilters(itemPage, itemSearchCriteria);
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
    public Item save(Item obj, Authentication authentication) {
        User user = userService.findByLogin(authentication.getName());
        obj.setUser(user);
        obj.setDate(LocalDate.now());
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
