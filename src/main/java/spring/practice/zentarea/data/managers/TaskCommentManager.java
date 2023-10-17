package spring.practice.zentarea.data.managers;

import spring.practice.zentarea.model.Comment;
import spring.practice.zentarea.model.Task;
import spring.practice.zentarea.utils.exceptions.CommentNotFoundException;
import spring.practice.zentarea.utils.exceptions.TaskNotFoundException;

import java.util.List;

/**
 * TaskCommentManager is an interface that defines the methods that can be used to manage comments for a task
 */
public interface TaskCommentManager {

    /**
     * Adds a comment to a task, and returns the created task
     *
     * @param comment - the comment to add to the task
     * @param taskId  - the id of the task to add the comment to
     * @return {@link Comment}
     **/
    Comment addCommentToTask(String comment, Long taskId);

    /**
     * Returns all the comments for a task
     *
     * @param taskId - the id of the task to get the comments for
     * @return {@link List}<{@link Comment}>
     * @throws TaskNotFoundException - if the task is not found
     **/
    List<Comment> getCommentsForTask(Long taskId) throws TaskNotFoundException;

//    /**
//     * Returns a specific comment for a task
//     *
//     * @param taskId    - the id of the task to get the comment for
//     * @param commentId - the id of the comment to get
//     * @return {@link Task}
//     * @throws TaskNotFoundException - if the task is not found
//     **/
//    Task getCommentForTask(Long taskId, Long commentId) throws TaskNotFoundException;

    /**
     * Updates a specific comment for a task, and returns the updated task
     *
     * @param taskId    - the id of the task to update the comment for
     * @param commentId - the id of the comment to update
     * @param comment   - the updated comment
     * @return {@link Task}
     * @throws CommentNotFoundException - if the comment is not found
     **/
    Comment updateCommentForTask(Long taskId, Long commentId, String comment) throws CommentNotFoundException;

    /**
     * Deletes a specific comment for a task
     *
     * @param taskId    - the id of the task to delete the comment for
     * @param commentId - the id of the comment to delete
     * @throws CommentNotFoundException - if the comment is not found
     **/
    void deleteComment(Long taskId, Long commentId) throws CommentNotFoundException;

    /**
     * Deletes all the comments for a task
     *
     * @param taskId - the id of the task to delete the comments for
     * @throws TaskNotFoundException - if the task is not found
     */
    void deleteAllCommentsForTask(Long taskId) throws TaskNotFoundException;
}
