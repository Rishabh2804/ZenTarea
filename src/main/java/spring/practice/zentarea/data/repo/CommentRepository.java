package spring.practice.zentarea.data.repo;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import spring.practice.zentarea.model.Comment;
import spring.practice.zentarea.utils.exceptions.CommentNotFoundException;
import spring.practice.zentarea.utils.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id")
    List<Comment> findByTaskID(@Param("task_id") Long taskId) throws TaskNotFoundException;

    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id AND c.cmtId = :cmt_id")
    Optional<Comment> findByTaskIDAndCmtID(@Param("task_id") Long taskId, @Param("cmt_id") Long cmtId) throws TaskNotFoundException, CommentNotFoundException;

    void deleteAllByTaskId(Long taskId) throws TaskNotFoundException;
}
