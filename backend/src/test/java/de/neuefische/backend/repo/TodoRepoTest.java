package de.neuefische.backend.repo;

import de.neuefische.backend.Todo.Todo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TodoRepoTest {

    @Test
    public void testGetTodos() {
        // given
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(new Todo("1", "Write unit tests", "doing"));
        todoRepo.addTodo(new Todo("2", "Write integration tests", "todo"));
        todoRepo.addTodo(new Todo("3", "Write methods", "done"));

        // when
        List<Todo> actual = todoRepo.getTodos();

        // then
        List<Todo> expected = List.of(
                new Todo("1", "Write unit tests", "doing"),
                new Todo("2", "Write integration tests", "todo"),
                new Todo("3", "Write methods", "done")
        );
        assertEquals(expected, actual);
    }


    @Test
    public void testAddTodo() {
        // given
        TodoRepo todoRepo = new TodoRepo();
        Todo todoToAdd = new Todo("2", "Write unit tests", "doing");

        // when
        Todo actual = todoRepo.addTodo(todoToAdd);

        // then
        List<Todo> expectedList = List.of(new Todo("1", "Write unit tests", "doing"));

        assertEquals(expectedList, todoRepo.getTodos());
        assertEquals(new Todo("1", "Write unit tests", "doing"), actual);
        assertNotEquals(new Todo("2", "Write unit tests", "doing"), actual);
    }


    @Test
    public void testGetByIdExistingId() {
        // given
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(new Todo("1", "Write unit tests", "doing"));
        todoRepo.addTodo(new Todo("2", "Write integration tests", "todo"));
        todoRepo.addTodo(new Todo("3", "Write methods", "done"));

        // when
        Optional<Todo> actual = todoRepo.getById("2");
        Optional<Todo> expected = Optional.of(new Todo("2", "Write integration tests", "todo"));

        // then
        assertTrue(actual.isPresent());
        assertEquals(expected, actual);
    }


    @Test
    public void testGetByIdNonExistingId() {
        // given
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(new Todo("1", "Write unit tests", "doing"));
        todoRepo.addTodo(new Todo("2", "Write integration tests", "todo"));
        todoRepo.addTodo(new Todo("3", "Write methods", "done"));

        // when
        Optional<Todo> actual = todoRepo.getById("4");

        // then
        assertTrue(actual.isEmpty());
    }


    @Test
    public void testUpdateTodo() {
        // given
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(new Todo("1", "Write unit tests", "doing"));
        todoRepo.addTodo(new Todo("2", "Write integration tests", "todo"));
        todoRepo.addTodo(new Todo("3", "Write methods", "done"));

        // when
        Todo updatedTodo = new Todo("1", "Write unit tests", "done");
        Todo actual = todoRepo.updateTodo("1", updatedTodo);

        // then
        List<Todo> expectedList = List.of(
                new Todo("1", "Write unit tests", "done"),
                new Todo("2", "Write integration tests", "todo"),
                new Todo("3", "Write methods", "done")
                );

        assertEquals(expectedList, todoRepo.getTodos());
        assertEquals(updatedTodo, actual);
    }


    @Test
    public void testDeleteTodo() {
        // given
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(new Todo("1", "Write unit tests", "doing"));
        todoRepo.addTodo(new Todo("2", "Write integration tests", "todo"));
        todoRepo.addTodo(new Todo("3", "Write methods", "done"));

        // when
        todoRepo.deleteTodo("2");

        // then
        List<Todo> expectedList = List.of(
                new Todo("1", "Write unit tests", "doing"),
                new Todo("3", "Write methods", "done")
        );
        assertEquals(expectedList, todoRepo.getTodos());
    }


}