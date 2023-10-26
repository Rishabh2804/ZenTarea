package spring.practice.zentarea.data.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.lang.*;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.repo.*;
import spring.practice.zentarea.model.*;
import spring.practice.zentarea.utils.exceptions.*;

@RestController
@RequestMapping("/tasks")
public final class TaskController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private void validateTask(@NonNull Long taskId) throws TaskNotFoundException {
        if (!taskRepository.existsById(taskId)) throw new TaskNotFoundException(taskId);
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
    public ResponseEntity<Task> getTask(@PathVariable @NonNull Long taskId) throws TaskNotFoundException {
        validateTask(taskId);

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
    public void deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {
        validateTask(taskId);
        taskRepository.deleteById(taskId);
    }


}
