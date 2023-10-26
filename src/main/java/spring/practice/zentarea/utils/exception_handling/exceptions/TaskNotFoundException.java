package spring.practice.zentarea.utils.exception_handling.exceptions;

public class TaskNotFoundException extends Exception {

    public TaskNotFoundException() {
        super("Bad Request! Task not found");
    }

    public TaskNotFoundException(Long id) {
        super("Bad Request! Task with id " + id + " not found");
    }
}
