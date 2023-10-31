package spring.practice.zentarea.data.service;

import spring.practice.zentarea.model.*;
import spring.practice.zentarea.utils.exceptions.*;

import java.util.*;

public interface CommentService {

    /**
     * Adds a comment to a task, and returns the created task
     *
     * @param commentText the comment to add to the task
     * @param taskId      the id of the task to add the comment to
     * @return {@link Comment}
     **/
    Comment addCommentToTask(String commentText, Long taskId) throws TaskNotFoundException;

    /**
     * Returns all the comments for a task
     *
     * @param taskId the id of the task to get the comments for
     * @return {@link List}<{@link Comment}>
     * @throws TaskNotFoundException if the task is not found
     **/
    List<Comment> getAllCommentsForTask(Long taskId) throws TaskNotFoundException;

    /**
     * Updates a specific comment for a task, and returns the updated task
     *
     * @param taskId      the id of the task to update the comment for
     * @param commentId   the id of the comment to update
     * @param commentText the updated comment
     * @return {@link Integer} - count of updated records
     * @throws CommentNotFoundException if the comment is not found
     **/
    Integer updateCommentForTask(
            Long taskId,
            Long commentId,
            String commentText
    ) throws CommentNotFoundException, TaskNotFoundException;

    /**
     * Deletes a specific comment for a task
     *
     * @param taskId    the id of the task to delete the comment for
     * @param commentId the id of the comment to delete
     * @throws TaskNotFoundException    if the task is not found
     * @throws CommentNotFoundException if the comment is not found
     **/
    void deleteComment(Long taskId, Long commentId) throws CommentNotFoundException, TaskNotFoundException;

    /**
     * Deletes all the comments for a task
     *
     * @param taskId the id of the task to delete the comments for
     * @throws TaskNotFoundException if the task is not found
     */
    void deleteAllCommentsForTask(Long taskId) throws TaskNotFoundException;
}
