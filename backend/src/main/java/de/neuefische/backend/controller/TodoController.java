package de.neuefische.backend.controller;

import de.neuefische.backend.Todo.Todo;
import de.neuefische.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping
    public List<Todo> getTodoList() {
        return todoService.getTodos();
    }


    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        todoService.addTodo(todo);
        return todo;
    }


    @PutMapping("{id}")
    public Todo updateTodo(@PathVariable String id, @RequestBody Todo todo) {
        todoService.updateTodo(id, todo);
        return todo;
    }


    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }


}
