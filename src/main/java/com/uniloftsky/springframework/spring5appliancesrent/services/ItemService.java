package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemPage;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public interface ItemService extends GenericService<Item, Long> {

    Set<Item> findAllActive();
    TreeSet<Item> findAllSortedById(Comparator<Item> comparator);
    TreeSet<Item> getLimitedCountPosts(Comparator<Item> comparator, int count);
    TreeSet<Item> getLastPostsIndexPage();
    Set<Item> getSimilarPosts();
    Page<Item> getCatalogItems(ItemPage employeePage, ItemSearchCriteria employeeSearchCriteria);
    Set<Item> searchBox(String name, String category, String type, String desc);
    Item save(Item obj, Authentication authentication, MultipartFile file) throws IOException;

}
