package spring.practice.zentarea.data.repo;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import spring.practice.zentarea.model.*;
import spring.practice.zentarea.utils.exceptions.*;

import java.util.*;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id")
    List<Comment> findByTaskID(@Param("task_id") Long taskId) throws TaskNotFoundException;

    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id AND c.cmtId = :cmt_id")
    Optional<Comment> findByTaskIDAndCmtID(@Param("task_id") Long taskId, @Param("cmt_id") Long cmtId) throws TaskNotFoundException, CommentNotFoundException;

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Comment c SET c.comment = :commentText WHERE c.taskId = :task_id AND c.cmtId = :cmt_id")
    int updateCommentByCmtId(@Param("task_id") Long taskId, @Param("cmt_id") Long cmtId, String commentText) throws TaskNotFoundException, CommentNotFoundException;

    void deleteAllByTaskId(@Param("task_id") Long taskId) throws TaskNotFoundException;

    void deleteCommentByTaskIdAndCmtId(@Param("task_id") Long taskId, @Param("cmt_id") Long cmtId) throws TaskNotFoundException, CommentNotFoundException;
}
