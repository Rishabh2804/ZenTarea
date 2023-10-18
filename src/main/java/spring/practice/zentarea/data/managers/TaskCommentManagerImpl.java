package spring.practice.zentarea.data.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import spring.practice.zentarea.data.repo.CommentRepository;
import spring.practice.zentarea.data.repo.TaskRepository;
import spring.practice.zentarea.model.Comment;
import spring.practice.zentarea.utils.exceptions.CommentNotFoundException;
import spring.practice.zentarea.utils.exceptions.TaskNotFoundException;

import java.util.List;

@Service
public class TaskCommentManagerImpl implements TaskCommentManager {
    private final CommentRepository commentRepository;

    @Autowired
    public TaskCommentManagerImpl(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @PostMapping("/{taskId}/comments")
    public Comment addCommentToTask(@RequestBody String commentText, @PathVariable Long taskId) {
        Comment comment = new Comment(taskId, commentText);
        commentRepository.save(comment);

        return commentRepository.save(comment);
    }

    @Override
    @GetMapping("/{taskId}/comments")
    public List<Comment> getCommentsForTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return commentRepository.findByTaskID(taskId);
    }

    @Override
    @PutMapping("/{taskId}/comments/{commentId}")
    public Comment updateCommentForTask(@PathVariable Long taskId, @PathVariable Long commentId, @RequestBody String comment) throws CommentNotFoundException {
        Comment existingComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        existingComment.setComment(comment);

        return commentRepository.save(existingComment);
    }

    @Override
    @DeleteMapping("/{taskId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long taskId, @PathVariable Long commentId) throws CommentNotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
    }

    @Override
    @DeleteMapping("/{taskId}/comments")
    public void deleteAllCommentsForTask(@PathVariable Long taskId) throws TaskNotFoundException {

    }
}
