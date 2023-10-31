package spring.practice.zentarea.data.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.lang.*;
import org.springframework.stereotype.*;
import spring.practice.zentarea.data.repo.*;
import spring.practice.zentarea.model.*;
import spring.practice.zentarea.utils.exceptions.*;
import spring.practice.zentarea.utils.logging.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Validates the existence of a task
     *
     * @param taskId the id of the task to validate
     * @throws TaskNotFoundException if the task is not found
     */
    private void validateTask(@NonNull Long taskId) throws TaskNotFoundException {
        if (!taskRepository.existsById(taskId)) throw new TaskNotFoundException(taskId);
    }

    public Task createTask(Task task) {
        return
                taskRepository.save(task);
    }

    public Task getTask(Long taskId) throws TaskNotFoundException {
        validateTask(taskId);

        return taskRepository.findById(taskId)
                        .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public Task updateTask(Long taskId, Task updateTask) throws TaskNotFoundException {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

        existingTask.update(updateTask);
        ZenLogger.logI("Updated task with id " + taskId);
        ZenLogger.logI("Updated task now:  " + existingTask);

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) throws TaskNotFoundException {
        validateTask(taskId);
        taskRepository.deleteById(taskId);
    }

}
