package spring.practice.zentarea.model;

import jakarta.persistence.*;
import spring.practice.zentarea.utils.exceptions.NotImplementedException;

import java.util.Date;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Enumerated(EnumType.ORDINAL)
    private TaskPriority priority;

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority taskPriority) {
        this.priority = taskPriority;
    }

    public void setPriority(int priority) {
        this.priority = TaskPriority.values()[priority];
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<Comment> comments;

    public Task() {
        priority = TaskPriority.MEDIUM;
    }

    public Task(String title, String description, Date dueDate, String status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        if (this.comments == null) this.comments = List.of(comment);
        else this.comments.add(comment);
    }

    public String getComment(int index) throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void removeComment(int index) throws IndexOutOfBoundsException {
        this.comments.remove(index);
    }

    public void update(Task updateTask) {
        this.title = updateTask.title;
        this.description = updateTask.description;
        this.dueDate = updateTask.dueDate;
        this.priority = updateTask.priority;
        this.status = updateTask.status;
    }

    public enum TaskPriority {
        HIGH(1), MEDIUM(2), LOW(3);

        private final int priority;

        TaskPriority(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }
}
