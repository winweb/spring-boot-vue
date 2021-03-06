package com.hobby.springbootvue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/todos")
class TodoController {

    @Resource
    private TodoRepository repository;

    @GetMapping
    Flux<Todo> last10Records() {
        return repository.last10Records();
    }

    @GetMapping("/{id}")
    Mono<Todo> get(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @PostMapping
    Mono<Todo> addTodo(@RequestBody Todo todo) {
        return repository.save(todo);
    }

    @PutMapping("/{id}")
    Mono<Todo> updateTodo(@PathVariable("id") Long id, @RequestBody Todo todo) {
        return repository.save(todo);
    }

    @DeleteMapping("/{id}")
    Mono<Void> deleteById(@PathVariable("id") Long id) {
        return repository.deleteById(id);
    }
}
