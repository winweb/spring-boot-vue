package com.hobby.springbootvue;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient(timeout = "10000")
@ActiveProfiles("test")
public class SpringBootVueApplicationTests {

	@MockBean
	TodoRepository repository;

	@Autowired
	WebTestClient webTestClient;

	@Test
	@DisplayName("First Test Case")
	public void testRepository() throws Exception {
		Mockito
				.when(repository.findById(1L))
				.thenReturn(Mono.just(Todo.builder().title("Buy milk").completed(false).build()));


		webTestClient.get()
				.uri("/todos/1")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Todo.class)
				.value(todo -> todo.getTitle(), equalTo("Buy milk"));
	}
}