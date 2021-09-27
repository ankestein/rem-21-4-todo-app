package de.neuefische.backend;

import de.neuefische.backend.Todo.Todo;
import de.neuefische.backend.controller.TodoController;
import de.neuefische.backend.repo.TodoRepo;
import de.neuefische.backend.service.TodoService;

public class MainApp {

    public static void main(String[] args) {
        TodoRepo todoRepo = new TodoRepo();
//        todoRepo.addTodo(new Todo("1", "Write unit tests", "doing"));
//        todoRepo.addTodo(new Todo("2", "Write integration tests", "todo"));
//        todoRepo.addTodo(new Todo("3", "Write methods", "done"));
//        System.out.println(todoRepo.getTodos());

//        todoRepo.updateTodo("3", new Todo("3", "Write Repo methods", "doing"));
//        System.out.println(todoRepo.getTodos());

        TodoService todoService = new TodoService(todoRepo);
        TodoController todoController = new TodoController(todoService);

        todoController.addTodo(new Todo("1", "Write unit tests", "doing"));
        todoController.addTodo(new Todo("2", "Write integration tests", "todo"));
        todoController.addTodo(new Todo("3", "Write methods", "done"));


    }
}
