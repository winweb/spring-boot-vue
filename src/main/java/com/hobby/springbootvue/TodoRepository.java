package com.hobby.springbootvue;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

interface TodoRepository extends ReactiveCrudRepository<Todo, Long> {

    @Query("SELECT * FROM TODO ORDER BY ID DESC LIMIT 10")
    Flux<Todo> last10Records();
}