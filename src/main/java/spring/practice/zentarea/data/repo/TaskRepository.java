package spring.practice.zentarea.data.repo;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import spring.practice.zentarea.model.*;

@SuppressWarnings("unused")
@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Long> {

    // TODO : figure out a way to use this and replace manual handling
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Task t SET t = :task WHERE t.taskId = :task_id")
    int updateTaskByTaskId(@Param("task_id") Long taskId, Task task);
}

