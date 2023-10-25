package spring.practice.zentarea.data.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.repo.*;
import spring.practice.zentarea.model.*;
import spring.practice.zentarea.utils.exceptions.*;

import java.util.logging.*;

@RestController
@RequestMapping("/tasks")
public final class TaskController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Creates a task
     *
     * @param task - the task to create
     * @return {@link ResponseEntity}<{@link Task}>
     */
    @PostMapping(
            produces = "application/json"
    )
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Logger logger = Logger.getLogger(TaskController.class.getName());
        logger.info("Creating task " + task.toString());
        return ResponseEntity.ok(taskRepository.save(task));
    }

    /**
     * Return task by taskId
     *
     * @param taskId - the id of the task to get
     * @return {@link ResponseEntity}<{@link Task}>
     * @throws TaskNotFoundException - if the task is not found
     */
    @GetMapping(value = "/{taskId}",
            produces = "application/json"
    )
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return ResponseEntity.ok(
                taskRepository.findById(taskId)
                        .orElseThrow(() -> new TaskNotFoundException(taskId))
        );
    }

    /**
     * Updates a task
     *
     * @param taskId     - the id of the task to update
     * @param updateTask - the updated task
     * @return {@link ResponseEntity}<{@link Task}>
     * @throws TaskNotFoundException - if the task is not found
     */
    @PutMapping(value = "/{taskId}",
            produces = "application/json"
    )
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updateTask) throws TaskNotFoundException {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

        existingTask.update(updateTask);

        return ResponseEntity.ok(taskRepository.save(existingTask));
    }

    /**
     * Deletes a task
     *
     * @param taskId - the id of the task to delete
     */
    @DeleteMapping(value = "/{taskId}",
            produces = "application/json"
    )
    public void deleteTask(@PathVariable Long taskId) {
        taskRepository.deleteById(taskId);
    }


}
