package com.ayoubtrd.links.controllers.data;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotEmpty;

public class CreateLinkBody {
    @NotEmpty
    private String slug;

    private String name;

    @NotEmpty
    @URL
    private String url;

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
