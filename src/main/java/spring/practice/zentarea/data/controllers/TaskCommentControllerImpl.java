package spring.practice.zentarea.data.controllers;

import org.apache.logging.log4j.util.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.repo.CommentRepository;
import spring.practice.zentarea.data.repo.TaskRepository;
import spring.practice.zentarea.model.Comment;
import spring.practice.zentarea.utils.exceptions.CommentNotFoundException;
import spring.practice.zentarea.utils.exceptions.TaskNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tasks/{taskId}/comments")
public class TaskCommentControllerImpl implements TaskCommentController {
    private final CommentRepository commentRepository;

    @Autowired
    public TaskCommentControllerImpl(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @PostMapping(produces = "application/json")
    public ResponseEntity<Comment> addCommentToTask(@RequestBody String commentText, @PathVariable Long taskId) {
        return ResponseEntity.ok(commentRepository.save(
                new Comment(taskId, commentText)
        ));
    }

    @Override
    @GetMapping(
            produces = "application/json"
    )
    public ResponseEntity<List<Comment>> getCommentsForTask(@PathVariable Long taskId) throws TaskNotFoundException {
        List<Comment> response = Optional.ofNullable(commentRepository.findByTaskID(taskId))
                .orElse(Collections.emptyList());

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping(value = "/{commentId}",
            produces = "application/json"
    )
    public ResponseEntity<Comment> updateCommentForTask(@PathVariable Long taskId, @PathVariable Long commentId, @RequestBody String comment) throws CommentNotFoundException {
        Comment existingComment = commentRepository
                        .findById(commentId)
                        .orElseThrow(() -> new CommentNotFoundException(commentId));

        existingComment.setComment(comment);

        return ResponseEntity.ok(commentRepository.save(existingComment));
    }

    @Override
    @DeleteMapping(value = "/{commentId}",
            produces = "application/json"
    )
    public void deleteComment(@PathVariable Long taskId, @PathVariable Long commentId) throws CommentNotFoundException, TaskNotFoundException {
        commentRepository.findByTaskIDAndCmtID(taskId, commentId)
                .orElseThrow(() -> new CommentNotFoundException(taskId, commentId));

        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    @DeleteMapping(
            produces = "application/json"
    )
    public void deleteAllCommentsForTask(@PathVariable Long taskId) throws TaskNotFoundException {
        commentRepository.deleteAllByTaskId(taskId);
    }
}
