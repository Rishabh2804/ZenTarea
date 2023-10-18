package spring.practice.zentarea.utils.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ZenExceptionHandler {

    @ExceptionHandler(value = TaskNotFoundException.class)
    public String TaskNotFoundExceptionHandler(TaskNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = CommentNotFoundException.class)
    public String CommentNotFoundExceptionHandler(CommentNotFoundException ex) {
        return ex.getMessage();
    }
}
