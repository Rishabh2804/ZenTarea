package spring.practice.zentarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.managers.TaskCommentManager;
import spring.practice.zentarea.data.repo.TaskRepository;
import spring.practice.zentarea.model.Task;
import spring.practice.zentarea.utils.exceptions.TaskNotFoundException;

@RestController
@RequestMapping("/tasks")
public final class TaskController {
    private final TaskRepository taskRepository;
    public final TaskCommentManager commentManager;

    @Autowired
    public TaskController(TaskRepository taskRepository, TaskCommentManager commentManager) {
        this.taskRepository = taskRepository;
        this.commentManager = commentManager;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updateTask) throws TaskNotFoundException {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);

        existingTask.update(updateTask);

        return taskRepository.save(existingTask);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskRepository.deleteById(taskId);
    }


}
