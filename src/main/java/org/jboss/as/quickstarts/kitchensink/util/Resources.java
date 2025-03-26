package com.example.kitchensink.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot equivalent of Jakarta EE Resources class.
 */
@Configuration
public class Resources {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public EntityManager entityManager() {
        return entityManager;
    }

    @Bean
    public Logger logger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
