package spring.practice.zentarea.data.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.lang.*;
import org.springframework.stereotype.*;
import spring.practice.zentarea.data.repo.*;
import spring.practice.zentarea.model.*;
import spring.practice.zentarea.utils.exceptions.*;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Validates the existence of a task
     *
     * @param taskId the id of the task to validate
     * @throws TaskNotFoundException if the task is not found
     */
    private void validateTask(@NonNull Long taskId) throws TaskNotFoundException {
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
    private void validateComment(@NonNull Long taskId, @NonNull Long commentId) throws TaskNotFoundException, CommentNotFoundException {
        validateTask(taskId);
        commentRepository.existsByTaskIdAndCmtId(taskId, commentId)
                .orElseThrow(() -> new CommentNotFoundException(taskId, commentId));
    }

    public Comment addCommentToTask(String commentText, Long taskId) throws TaskNotFoundException {
        validateTask(taskId);

        return commentRepository.save(new Comment(taskId, commentText));
    }

    public List<Comment> getAllCommentsForTask(Long taskId) throws TaskNotFoundException {
        validateTask(taskId);
        return commentRepository.findByTaskID(taskId);
    }

    public Integer updateCommentForTask(
            Long taskId,
            Long commentId,
            String commentText
    ) throws CommentNotFoundException, TaskNotFoundException {
        validateComment(taskId, commentId);

        commentRepository.findByTaskIDAndCmtID(taskId, commentId)
                .orElseThrow(() -> new CommentNotFoundException(taskId, commentId));

        return
                commentRepository
                        .updateCommentByCmtId(taskId, commentId, commentText);
    }

    public void deleteComment(Long taskId, Long commentId) throws CommentNotFoundException, TaskNotFoundException {
        validateComment(taskId, commentId);
        commentRepository.deleteCommentByTaskIdAndCmtId(taskId, commentId);
    }

    public void deleteAllCommentsForTask(Long taskId) throws TaskNotFoundException {
        validateTask(taskId);
        commentRepository.deleteAllByTaskId(taskId);
    }
}
