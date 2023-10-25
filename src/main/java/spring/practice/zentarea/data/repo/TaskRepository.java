package spring.practice.zentarea.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.practice.zentarea.model.Task;
import spring.practice.zentarea.model.TaskPriority;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> getAllByPriority(TaskPriority priority);
}
