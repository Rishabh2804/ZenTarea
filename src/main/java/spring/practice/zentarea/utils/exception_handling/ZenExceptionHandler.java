package spring.practice.zentarea.utils.exception_handling;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.utils.exception_handling.exceptions.*;

@ControllerAdvice
public class ZenExceptionHandler {

    @ExceptionHandler(value = TaskNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse TaskNotFoundExceptionHandler(TaskNotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CommentNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse CommentNotFoundExceptionHandler(CommentNotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
