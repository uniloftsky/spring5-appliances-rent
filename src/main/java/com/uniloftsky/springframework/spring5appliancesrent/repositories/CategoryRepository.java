package com.uniloftsky.springframework.spring5appliancesrent.repositories;

import com.uniloftsky.springframework.spring5appliancesrent.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
