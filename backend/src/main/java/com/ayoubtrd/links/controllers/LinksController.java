package com.ayoubtrd.links.controllers;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayoubtrd.links.entities.Link;
import com.ayoubtrd.links.services.LinkService;
import com.ayoubtrd.links.controllers.data.CreateLinkBody;


@RestController
@RequestMapping("/")
public class LinksController {

    private LinkService service;

    public LinksController(LinkService service) {
        this.service = service;
    }

    @GetMapping("{linkSlug}")
    public ResponseEntity<Void> link(@PathVariable String linkSlug) {
        try {
            Link link = service.getLinkBySlug(linkSlug);
            if (link == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.status(302).location(new URI(link.getUrl())).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Link> createLink(@RequestBody CreateLinkBody data) {
        Link link = service.create(data.getName(), data.getSlug(), data.getUrl());

        return ResponseEntity.ok(link);
    }
}
