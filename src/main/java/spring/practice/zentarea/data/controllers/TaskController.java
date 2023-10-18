package spring.practice.zentarea.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.repo.TaskRepository;
import spring.practice.zentarea.model.Task;
import spring.practice.zentarea.utils.exceptions.TaskNotFoundException;

@RestController
@RequestMapping("/tasks")
public final class TaskController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, TaskCommentController commentManager) {
        this.taskRepository = taskRepository;
    }

    @PostMapping(
            produces = "application/json"
    )
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskRepository.save(task));
    }

    @GetMapping(value = "/{taskId}",
            produces = "application/json"
    )
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return ResponseEntity.ok(
                taskRepository.findById(taskId)
                        .orElseThrow(TaskNotFoundException::new)
        );
    }

    @PutMapping(value = "/{taskId}",
            produces = "application/json"
    )
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updateTask) throws TaskNotFoundException {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);

        existingTask.update(updateTask);

        return ResponseEntity.ok(taskRepository.save(existingTask));
    }

    @DeleteMapping(value = "/{taskId}",
            produces = "application/json"
    )
    public void deleteTask(@PathVariable Long taskId) {
        taskRepository.deleteById(taskId);
    }


}
