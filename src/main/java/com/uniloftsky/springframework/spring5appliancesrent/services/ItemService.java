package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemPage;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public interface ItemService {

    Set<Item> findAll();
    Set<Item> findAllActive();
    Set<Item> findAllSortedById(Comparator<Item> comparator);
    Set<Item> getLimitedCountPosts(Comparator<Item> comparator, int count);
    Set<Item> getLastPostsIndexPage();
    Set<Item> getSimilarPosts();
    Page<Item> findAll(Pageable pageable);
    Page<Item> getCatalogItems(ItemPage employeePage, ItemSearchCriteria employeeSearchCriteria);
    Item findById(Long id);
    Item save(Item obj, Authentication authentication);
    List<Item> saveAll(List<Item> items);
    void delete(Item obj);

}
