package com.uniloftsky.springframework.spring5appliancesrent.repositories;

import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
