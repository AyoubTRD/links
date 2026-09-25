package com.ayoubtrd.links.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ayoubtrd.links.entities.Link;

public interface LinkRepository extends CrudRepository<Link, Long> {
    public Link findBySlug(String slug);
}
