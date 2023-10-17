package spring.practice.zentarea.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Date dueDate;
    private String status;

    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    public Task() {
        priority = Priority.MEDIUM;
    }

    public Task(String title, String description, Date dueDate, String status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setPriority(int priority) {
        this.priority = Priority.values()[priority];
    }

    public void update(Task updateTask) {
        this.title = updateTask.title;
        this.description = updateTask.description;
        this.dueDate = updateTask.dueDate;
        this.priority = updateTask.priority;
        this.status = updateTask.status;
    }
}
