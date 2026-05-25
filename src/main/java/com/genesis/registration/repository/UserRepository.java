package com.genesis.registration.repository;

import com.genesis.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existingByPersonId(String personId);
}
