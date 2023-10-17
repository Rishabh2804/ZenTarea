package spring.practice.zentarea.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.practice.zentarea.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
