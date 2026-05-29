package com.genesis.registration.repository;

import com.genesis.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPersonId(String personId);
    boolean existsByUuid(String uuid);
}
