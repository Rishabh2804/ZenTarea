package spring.practice.zentarea.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.repo.TaskRepository;
import spring.practice.zentarea.model.Task;
import spring.practice.zentarea.model.TaskPriority;
import spring.practice.zentarea.utils.converters.TaskPriorityEditor;
import spring.practice.zentarea.utils.exceptions.TaskNotFoundException;

import java.util.logging.Logger;

@RestController
@RequestMapping("/tasks")
public final class TaskController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(TaskPriority.class, new TaskPriorityEditor());
    }

    @GetMapping(
            value = "/priority",
            produces = "application/json"
    )
    public ResponseEntity<String> getTasks(@RequestParam(required = false) TaskPriority priority) {
        return ResponseEntity.ok(priority.name());
    }

    @PostMapping(
            produces = "application/json"
    )
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Logger logger = Logger.getLogger(TaskController.class.getName());
        logger.info("Creating task " + task.toString());
        return ResponseEntity.ok(taskRepository.save(task));
    }

    @GetMapping(value = "/{taskId}",
            produces = "application/json"
    )
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return ResponseEntity.ok(
                taskRepository.findById(taskId)
                        .orElseThrow(() -> new TaskNotFoundException(taskId))
        );
    }

    @PutMapping(value = "/{taskId}",
            produces = "application/json"
    )
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updateTask) throws TaskNotFoundException {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

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
