package de.neuefische.backend.controller;

import de.neuefische.backend.Todo.Todo;
import de.neuefische.backend.repo.TodoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TodoRepo todoRepo;

    @BeforeEach
    public void clearRepo() {
        todoRepo.clearRepo();
    }

    @Test
    public void testGetTodos() {
        // given
        todoRepo.addTodo(new Todo("1", "Write unit tests", "doing"));
        todoRepo.addTodo(new Todo("2", "Write integration tests", "todo"));
        todoRepo.addTodo(new Todo("3", "Write methods", "done"));

        // when
        ResponseEntity<Todo[]> response = restTemplate.getForEntity("/api/todo", Todo[].class);

        // then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(
                new Todo("1", "Write unit tests", "doing"),
                new Todo("2", "Write integration tests", "todo"),
                new Todo("3", "Write methods", "done")
        ));
    }

    @Test
    void testAddTodo() {
        // given
        String url = "http://localhost:" + port + "/api/todo";
        Todo todoToAdd = new Todo("2", "Write integration tests", "todo");

        // when
        ResponseEntity<Todo> response = restTemplate.postForEntity(url, todoToAdd, Todo.class);

        // then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(not(new Todo("2", "Write integration tests", "todo"))));
        assertThat(response.getBody(), is(new Todo("1", "Write integration tests", "todo")));
        Optional<Todo> addedTodo = todoRepo.getById("1");
        assertThat(addedTodo.get(), is(new Todo("1", "Write integration tests", "todo")));
    }


    @Test
    void testUpdateTodo() {
        // given
        String url = "http://localhost:" + port + "/api/todo/1";
        todoRepo.addTodo(new Todo("1", "Write unit tests", "doing"));
        todoRepo.addTodo(new Todo("2", "Write integration tests", "todo"));
        todoRepo.addTodo(new Todo("3", "Write methods", "done"));
        Todo todoToUpdate = new Todo("1", "Write unit tests", "done");

        // when
        HttpEntity<Todo> entity = new HttpEntity<>(todoToUpdate);
        ResponseEntity<Todo> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Todo.class);

        // then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(todoToUpdate));
        Optional<Todo> updatedTodo = todoRepo.getById("1");
        assertThat(updatedTodo.get(), is(todoToUpdate));
     }


    @Test
    void testDeleteTodo() {
        // given
        String url = "http://localhost:" + port + "/api/todo/3";
        todoRepo.addTodo(new Todo("1", "Write unit tests", "doing"));
        todoRepo.addTodo(new Todo("1", "Write integration tests", "todo"));
        todoRepo.addTodo(new Todo("2", "Write methods", "done"));

        // when
        restTemplate.delete(url);

        assertThat(todoRepo.getById("3"), is(Optional.empty()));
        assertThat(todoRepo.getTodos(), is(List.of(
                new Todo("1", "Write unit tests", "doing"),
                new Todo("2", "Write integration tests", "todo")
        )));

    }
}