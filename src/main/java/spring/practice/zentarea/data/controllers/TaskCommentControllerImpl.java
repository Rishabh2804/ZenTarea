package spring.practice.zentarea.data.controllers;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.repo.CommentRepository;
import spring.practice.zentarea.data.repo.TaskRepository;
import spring.practice.zentarea.model.Comment;
import spring.practice.zentarea.utils.exceptions.CommentNotFoundException;
import spring.practice.zentarea.utils.exceptions.TaskNotFoundException;

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
        Comment comment = new Comment(taskId, commentText);

        return ResponseEntity.ok(commentRepository.save(comment));
    }

    @Override
    @GetMapping(
            produces = "application/json"
    )
    public ResponseEntity<List<Comment>> getCommentsForTask(@PathVariable Long taskId) throws TaskNotFoundException {
        List<Comment> response = Optional.ofNullable(commentRepository.findByTaskID(taskId))
                .orElseThrow(TaskNotFoundException::new);

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping(value = "/{commentId}",
            produces = "application/json"
    )
    public ResponseEntity<Comment> updateCommentForTask(@PathVariable Long taskId, @PathVariable Long commentId, @RequestBody String comment) throws CommentNotFoundException {
        Comment existingComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        existingComment.setComment(comment);

        return ResponseEntity.ok(commentRepository.save(existingComment));
    }

    @Override
    @DeleteMapping(value = "/{commentId}",
            produces = "application/json"
    )
    public void deleteComment(@PathVariable Long taskId, @PathVariable Long commentId) throws CommentNotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
        commentRepository.deleteById(commentId);
    }

    @Override
    @DeleteMapping(
            produces = "application/json"
    )
    public void deleteAllCommentsForTask(@PathVariable Long taskId) throws TaskNotFoundException {
        commentRepository.deleteAllByTaskId(taskId);
    }
}
