package spring.practice.zentarea.utils.exceptions;

public class CommentNotFoundException extends Exception {

    public CommentNotFoundException() {
        super("Bad Request! Comment doesn't exist");
    }

    public CommentNotFoundException(Long cmtID) {
        super("Bad Request! Comment with cmtID - " + cmtID + " doesn't exist");
    }

    public CommentNotFoundException(Long taskId, Long cmtId) {
        super("Bad Request! Comment with cmtID - " + cmtId + " doesn't exists, or is not associated with task with taskId - " + taskId);
    }
}
