package de.neuefische.backend.repo;

import de.neuefische.backend.Todo.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepo {
    List<Todo> todoList = new ArrayList<>();

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


    /*
    public Optional<Todo> getById(String id) {
        for (Todo todo : todoList) {
            if (id.equals(todo.getId())) {
                return Optional.of(todo);
            }
        }
        return Optional.empty();
    }
    */

    public Optional<Todo> getById(String id) {
        return todoList.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    public Todo updateTodo(String id, Todo todo) {
        if (getById(id).isEmpty()) {
            throw new NullPointerException();
        }
        if (!id.equals(todo.getId())) {
            throw new IllegalArgumentException();
        }
        Collections.replaceAll(todoList, getById(id).get(), todo);
        return todo;
    }


    public void deleteTodo(String id) {
        if (getById(id).isEmpty()) {
            throw new NullPointerException();
        }
        Optional<Todo> todo = getById(id);
        todoList.remove(todo.get());
    }


    public void clearRepo() {
        todoList.clear();
    }
}
