package de.neuefische.backend.service;

import de.neuefische.backend.Todo.Todo;
import de.neuefische.backend.repo.TodoRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    private final TodoRepo todoRepo = mock(TodoRepo.class);


    @Test
    public void testGetTodos() {
        // given
        when(todoRepo.getTodos()).thenReturn(
                List.of(
                        new Todo("1", "write unit tests", "doing"),
                        new Todo("2", "Write integration tests", "todo"),
                        new Todo("3", "Write methods", "done")
        ));
       TodoService todoService = new TodoService(todoRepo);

        // when
        List<Todo> actual = todoService.getTodos();

        // then
        assertEquals(List.of(
                new Todo("1", "write unit tests", "doing"),
                new Todo("2", "Write integration tests", "todo"),
                new Todo("3", "Write methods", "done")
                ), actual);
        verify(todoRepo).getTodos();
    }


    @Test
    public void testAddTodo() {
        // given
        Todo todoToAdd = new Todo("4", "Git push", "todo");
        when(todoRepo.addTodo(todoToAdd)).thenReturn(todoToAdd);
        TodoService todoService = new TodoService(todoRepo);

        // when
        Todo actual = todoService.addTodo(todoToAdd);

        // then
        assertEquals(todoToAdd, actual);
        verify(todoRepo).addTodo(todoToAdd);

        //List<Todo> expectedList = List.of(
        //        new Todo("4", "Git push", "todo")
        //);
        // how to compare the resulting lists => with mocking as above, I can only test if object was returned as expected =>
        // is that all I want to test for Service, because I compare the lists in TodoRepo and use verify?
        //assertEquals(expectedList, todoService.getTodos());
   }


    @Test
    public void testUpdateTodo() {
        // given
        Todo todoToUpdate = new Todo("4", "Git push", "todo");
        when(todoRepo.updateTodo("4", todoToUpdate)).thenReturn(todoToUpdate);
        TodoService todoService = new TodoService(todoRepo);

        // when
        Todo actual = todoService.updateTodo("4", todoToUpdate);


        // then
        assertEquals(todoToUpdate, actual);
        verify(todoRepo).updateTodo("4", todoToUpdate);
    }


    @Test
    public void testDeleteTodo() {
        // given
        when(todoRepo.getById("3")).thenReturn(Optional.of(new Todo("3", "Write methods", "done")));
        TodoService todoService = new TodoService(todoRepo);

        // when
        todoService.deleteTodo("3");

        // then
        verify(todoRepo).deleteTodo("3");
    }
}