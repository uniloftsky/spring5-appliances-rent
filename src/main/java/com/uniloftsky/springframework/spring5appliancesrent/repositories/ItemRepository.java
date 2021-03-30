package com.uniloftsky.springframework.spring5appliancesrent.repositories;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAll(Pageable pageable);
    Set<Item> findAllByNameIsLikeOrCategory_CategoryNameIsLikeOrCategory_Type_TypeNameIsLikeOrDescriptionIsLikeAllIgnoreCase(String name, String category, String type, String desc);

}
