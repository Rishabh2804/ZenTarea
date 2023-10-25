package spring.practice.zentarea.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    private Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    public TaskPriority getPriority() {
        return priority;
    }

    // Setter necessary for custom editor,
    // else default implementation will be followed for setting the value
    // for the priority field
    public void setPriority(String priority) {
        this.priority = TaskPriority.parse(priority);
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public Task() {
        priority = TaskPriority.MEDIUM;
    }

    public void update(Task updateTask) {
        this.title = updateTask.title;
        this.description = updateTask.description;
        this.dueDate = updateTask.dueDate;
        this.priority = updateTask.priority;
        this.status = updateTask.status;
    }

}
