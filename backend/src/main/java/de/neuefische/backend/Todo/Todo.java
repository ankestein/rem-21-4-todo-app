package de.neuefische.backend.Todo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Todo {
    String id;
    String description;
    String status;
}
