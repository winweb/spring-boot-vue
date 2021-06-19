package com.hobby.springbootvue;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/*
    IDs are not returned by RestRepository by default. I like them
    exposed so that the client can easily find the ID of created
    and listed resources.
 */
@Component
public class RestRepositoryConfigurator implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Todo.class);
        
        // See https://github.com/spring-projects/spring-data-rest/issues/2023
        config.useHalAsDefaultJsonMediaType(false);
    }
}