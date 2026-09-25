package com.ayoubtrd.links.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "com.ayoubtrd.links.repositories" })
public class ApplicationConfig {

}
