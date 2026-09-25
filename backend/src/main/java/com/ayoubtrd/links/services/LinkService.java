package com.ayoubtrd.links.services;

import org.springframework.stereotype.Service;

import com.ayoubtrd.links.entities.Link;
import com.ayoubtrd.links.repositories.LinkRepository;

@Service
public class LinkService {
    private LinkRepository repo;

    public LinkService(LinkRepository repo) {
        this.repo = repo;
    }

    public Link getLinkBySlug(String slug) {
        Link link = repo.findBySlug(slug);

        return link;
    }

    public Link create(String name, String slug, String url) {
        Link link = new Link();

        link.setName(name);
        link.setSlug(slug);
        link.setUrl(url);

        this.repo.save(link);

        return link;
    }
} 
