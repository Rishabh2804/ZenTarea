package spring.practice.zentarea.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import spring.practice.zentarea.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id")
    List<Comment> findByTaskID(@Param("task_id") Long taskId);

    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id AND c.cmtId = :cmt_id")
    ResponseEntity<Comment> findByTaskIDAndCmtID(@Param("task_id") Long taskId, @Param("cmt_id") Long cmtId);

//    @Modifying
//    @Query("DELETE FROM Comment c WHERE c.taskId = :task_id")
//    void deleteAllByTaskId(@Param("task_id") Long taskId);
//
    void deleteAllByTaskId(Long taskId);
}
