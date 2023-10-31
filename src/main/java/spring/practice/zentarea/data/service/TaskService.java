package spring.practice.zentarea.data.service;

import spring.practice.zentarea.model.*;
import spring.practice.zentarea.utils.exceptions.*;

public interface TaskService {

    /**
     * Creates a task
     *
     * @param task - the task to create
     * @return {@link Task}
     */
    Task createTask(Task task);

    /**
     * Return task by taskId
     *
     * @param taskId - the id of the task to get
     * @return {@link Task}
     * @throws TaskNotFoundException - if the task is not found
     */
    Task getTask(Long taskId) throws TaskNotFoundException;

    /**
     * Updates a task
     *
     * @param taskId     - the id of the task to update
     * @param updateTask - the updated task
     * @return {@link Task}
     */
    Task updateTask(Long taskId, Task updateTask) throws TaskNotFoundException;

    /**
     * Deletes a task
     *
     * @param taskId - the id of the task to delete
     */
    void deleteTask(Long taskId) throws TaskNotFoundException;

}
