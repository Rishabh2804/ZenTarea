package spring.practice.zentarea.utils.exceptions;

public class CommentNotFoundException extends Exception {

    public CommentNotFoundException() {
        super("Comment not found");
    }

    public CommentNotFoundException(Long id) {
        super("Comment with id " + id + " not found");
    }

    public CommentNotFoundException(Long taskId, Long commentId) {
        super("Comment with id " + commentId + " not found for task with id " + taskId);
    }
}
