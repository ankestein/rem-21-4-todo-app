package de.neuefische.backend.Todo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Todo {
    private String id;
    private String description;
    private String status;

    // possible values of status in the frontend:
    // OPEN, IN_PROGRESS, DONE
    // in the frontend, only advance of status is possible!
}
