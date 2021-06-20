package com.hobby.springbootvue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
public class SpringBootVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVueApplication.class, args);
	}

	@Autowired
	TodoRepository repository;

	@Bean
	ApplicationRunner init(DatabaseClient client) {
		return args -> {
			client.sql("create table IF NOT EXISTS TODO" +
					"(id SERIAL PRIMARY KEY, text varchar (255) not null, completed boolean default false);").fetch().first().subscribe();

			client.sql("DELETE FROM TODO;").fetch().first().subscribe();

			client.sql("INSERT INTO TODO (title, completed) VALUES ('Buy milk', false)").fetch().first().subscribe();

			Stream<Todo> stream = Stream.of(Todo.builder().title("Buy milk").completed(false).build(),
											Todo.builder().title("This one I have acomplished!").completed(true).build(),
											new Todo(null, "And this is secret", false));

			// initialize the database
			Flux<Todo> todoFlux = repository.saveAll(Flux.fromStream(stream));

			if(todoFlux == null) {
				//prevent null exception when run test case.
				return;
			}

			todoFlux
					.then()
					.subscribe(); // execute

			repository.last10Records().toIterable().forEach(o -> {
				log.info(o.toString());
			});
		};
	}

	// Fix the CORS errors (for DEV)
	@Bean
	CorsWebFilter corsWebFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		corsConfig.setMaxAge(8000L);
		corsConfig.setAllowedMethods(Collections.singletonList("*"));
		corsConfig.setAllowedHeaders(Collections.singletonList("*"));

		org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}

	//fix HTML5 history mode in Vue
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
		return (factory) -> factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/index.html"));
	}
}