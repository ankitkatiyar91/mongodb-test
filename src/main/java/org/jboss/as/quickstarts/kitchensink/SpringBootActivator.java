package org.jboss.as.quickstarts.kitchensink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot equivalent of Jakarta EE's JAX-RS application activator.
 * Serves as the entry point for the application.
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.jboss.as.quickstarts.kitchensink")
public class SpringBootActivator {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActivator.class, args);
    }
}