package spring.practice.zentarea.data.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.repo.*;
import spring.practice.zentarea.model.*;
import spring.practice.zentarea.utils.exceptions.*;

import java.util.*;

@RestController
@RequestMapping("tasks/{taskId}/comments")
public class TaskCommentController {
    private final CommentRepository commentRepository;

    @Autowired
    public TaskCommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Validates the existence of a task
     *
     * @param taskId the id of the task to validate
     * @throws TaskNotFoundException if the task is not found
     */
    private void validateTask(Long taskId) throws TaskNotFoundException {
        commentRepository.existsTask(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    /**
     * Validates the existence of a comment
     *
     * @param taskId    the id of the task to validate
     * @param commentId the id of the comment to validate
     * @throws TaskNotFoundException    if the task is not found
     * @throws CommentNotFoundException if the comment is not found, or is not associated with given taskId
     */
    private void validateComment(Long taskId, Long commentId) throws TaskNotFoundException, CommentNotFoundException {
        validateTask(taskId);
        commentRepository.existsByTaskIdAndCmtId(taskId, commentId)
                .orElseThrow(() -> new CommentNotFoundException(taskId, commentId));
    }

    /**
     * Adds a comment to a task, and returns the created task
     *
     * @param commentText the comment to add to the task
     * @param taskId      the id of the task to add the comment to
     * @return {@link ResponseEntity}<{@link Comment}>
     **/
    @PostMapping(produces = "application/json")
    public ResponseEntity<Comment> addCommentToTask(@RequestBody String commentText, @PathVariable Long taskId) throws TaskNotFoundException {
        validateTask(taskId);

        return ResponseEntity.ok(commentRepository.save(
                new Comment(taskId, commentText)
        ));
    }

    /**
     * Returns all the comments for a task
     *
     * @param taskId the id of the task to get the comments for
     * @return {@link ResponseEntity}<{@link List}<{@link Comment}>>
     * @throws TaskNotFoundException if the task is not found
     **/
    @GetMapping(
            produces = "application/json"
    )
    public ResponseEntity<List<Comment>> getCommentsForTask(@PathVariable Long taskId) throws TaskNotFoundException {
        validateTask(taskId);
        return ResponseEntity
                .ok(commentRepository.findByTaskID(taskId));
    }

    /**
     * Updates a specific comment for a task, and returns the updated task
     *
     * @param taskId      the id of the task to update the comment for
     * @param commentId   the id of the comment to update
     * @param commentText the updated comment
     * @return {@link ResponseEntity}<{@link Comment}>
     * @throws CommentNotFoundException if the comment is not found
     **/
    @PutMapping(value = "/{commentId}",
            produces = "application/json"
    )
    public ResponseEntity<Integer> updateCommentForTask(
            @PathVariable Long taskId,
            @PathVariable Long commentId,
            @RequestBody String commentText
    ) throws CommentNotFoundException, TaskNotFoundException {
        validateComment(taskId, commentId);

        commentRepository.findByTaskIDAndCmtID(taskId, commentId)
                .orElseThrow(() -> new CommentNotFoundException(taskId, commentId));

        return ResponseEntity.ok(
                commentRepository
                        .updateCommentByCmtId(taskId, commentId, commentText)
        );
    }

    /**
     * Deletes a specific comment for a task
     *
     * @param taskId    the id of the task to delete the comment for
     * @param commentId the id of the comment to delete
     * @throws CommentNotFoundException if the comment is not found
     **/
    @DeleteMapping(value = "/{commentId}",
            produces = "application/json"
    )
    public void deleteComment(@PathVariable Long taskId, @PathVariable Long commentId) throws CommentNotFoundException, TaskNotFoundException {
        validateComment(taskId, commentId);
        commentRepository.deleteCommentByTaskIdAndCmtId(taskId, commentId);
    }

    /**
     * Deletes all the comments for a task
     *
     * @param taskId the id of the task to delete the comments for
     * @throws TaskNotFoundException if the task is not found
     */
    @Transactional
    @DeleteMapping(
            produces = "application/json"
    )
    public void deleteAllCommentsForTask(@PathVariable Long taskId) throws TaskNotFoundException {
        validateTask(taskId);
        commentRepository.deleteAllByTaskId(taskId);
    }
}
