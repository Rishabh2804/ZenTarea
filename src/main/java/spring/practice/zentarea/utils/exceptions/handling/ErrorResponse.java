package spring.practice.zentarea.utils.exceptions.handling;

import org.springframework.http.*;

import java.util.*;

public class ErrorResponse {
    private final Date timestamp;
    private HttpStatus status;
    private String error;

    public ErrorResponse() {
        this.timestamp = new Date();
    }

    public ErrorResponse(String error) {
        this();
        this.error = error;
    }

    public ErrorResponse(String error, HttpStatus status) {
        this(error);
        this.status = status;
    }

    public int getStatus() {
        return status.value();
    }

    public String getError() {
        return error;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
