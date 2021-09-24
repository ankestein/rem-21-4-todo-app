package de.neuefische.backend.repo;

import de.neuefische.backend.Todo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public class TodoRepo {
    private final List<Todo> todoList;

    public TodoRepo(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public List<Todo> getTodos() {
        return todoList;
    }

    private String generateId(){
        if(todoList.isEmpty()) {
            return "1";
        }
        int maxId = todoList.stream()
                .map((currentTodo) -> currentTodo.getId())
                .mapToInt(id -> Integer.parseInt(id))
                .max()
                .getAsInt();

        maxId++;
        return String.valueOf(maxId);
    }

    public Todo addTodo(Todo todo) {
        todo.setId(generateId());
        todoList.add(todo);
        return todo;
    }

    public void updateTodo(String id, Todo todo) {
    }

    public void deleteTodo(String id) {
    }
}
