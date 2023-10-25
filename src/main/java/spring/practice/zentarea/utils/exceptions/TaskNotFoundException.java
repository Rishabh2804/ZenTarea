package spring.practice.zentarea.utils.exceptions;

public class TaskNotFoundException extends Exception{

    public TaskNotFoundException(Long id) {
        super("Task with id " + id + " not found");
    }
}
