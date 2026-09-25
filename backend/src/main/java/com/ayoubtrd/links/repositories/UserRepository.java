package com.ayoubtrd.links.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ayoubtrd.links.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);

    public boolean existsByUsername(String username);
}

