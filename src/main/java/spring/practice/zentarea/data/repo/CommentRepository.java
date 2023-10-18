package spring.practice.zentarea.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.practice.zentarea.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id")
    List<Comment> findByTaskID(@Param("task_id") Long taskId);

}
