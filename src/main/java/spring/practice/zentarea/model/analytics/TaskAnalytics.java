package spring.practice.zentarea.model.analytics;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_analytics")
public class TaskAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private String priority;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Column(name = "completed_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedDate;

    @Column(name = "days_to_complete")
    private Integer daysToComplete;

    @Column(name = "comment_count")
    private Integer commentCount;

    // Default constructor
    public TaskAnalytics() {}

    // Constructor for creating analytics record from task data
    public TaskAnalytics(Long taskId, String title, String status, String priority, 
                        Date createdDate, Date dueDate, Date completedDate, 
                        Integer daysToComplete, Integer commentCount) {
        this.taskId = taskId;
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.daysToComplete = daysToComplete;
        this.commentCount = commentCount;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Integer getDaysToComplete() {
        return daysToComplete;
    }

    public void setDaysToComplete(Integer daysToComplete) {
        this.daysToComplete = daysToComplete;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}