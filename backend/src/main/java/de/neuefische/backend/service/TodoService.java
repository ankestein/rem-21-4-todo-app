package de.neuefische.backend.service;

import de.neuefische.backend.Todo.Todo;
import de.neuefische.backend.repo.TodoRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {

    private final TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<Todo> getTodos() {
        return todoRepo.getTodos();
    }

    public Todo addTodo(Todo todo) {
        return todoRepo.addTodo(todo);
    }

    public Todo updateTodo(String id, Todo todo) {
        return todoRepo.updateTodo(id, todo);
    }

    public void deleteTodo(String id) {
        todoRepo.deleteTodo(id);
    }
}
