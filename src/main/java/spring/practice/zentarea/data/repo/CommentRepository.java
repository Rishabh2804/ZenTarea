package spring.practice.zentarea.data.repo;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import spring.practice.zentarea.model.*;

import java.util.*;

@Repository("commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Validates the existence of a task
     * corresponding to the given taskId
     *
     * @param taskId - the id of the task to check
     * @return - true if the task exists, false otherwise
     */
    @Query("SELECT COUNT(t) FROM Task t WHERE t.taskId = :task_id")
    Optional<Boolean> existsTask(@Param("task_id") Long taskId);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.taskId = :task_id AND c.cmtId = :cmt_id")
    Optional<Boolean> existsByTaskIdAndCmtId(@Param("task_id") Long taskId, @Param("cmt_id") Long cmtId);

    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id")
    List<Comment> findByTaskID(@Param("task_id") Long taskId);

    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id AND c.cmtId = :cmt_id")
    Optional<Comment> findByTaskIDAndCmtID(@Param("task_id") Long taskId, @Param("cmt_id") Long cmtId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Comment c SET c.comment = :commentText WHERE c.taskId = :task_id AND c.cmtId = :cmt_id")
    int updateCommentByCmtId(@Param("task_id") Long taskId, @Param("cmt_id") Long cmtId, String commentText);

    @Transactional
    void deleteAllByTaskId(@Param("task_id") Long taskId);

    void deleteCommentByTaskIdAndCmtId(@Param("task_id") Long taskId, @Param("cmt_id") Long cmtId);
}
