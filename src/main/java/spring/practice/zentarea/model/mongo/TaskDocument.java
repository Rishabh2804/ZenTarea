package spring.practice.zentarea.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import spring.practice.zentarea.model.BaseEntity;
import spring.practice.zentarea.model.TaskPriority;

import java.util.Date;
import java.util.List;

@Document(collection = "tasks")
@SuppressWarnings("unused")
public class TaskDocument extends BaseEntity {
    
    @Id
    private String id;
    
    private String title;
    private String description;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dueDate;
    
    private String status;
    private TaskPriority priority;
    private List<CommentDocument> comments;
    
    public TaskDocument() {
        priority = TaskPriority.MEDIUM;
    }
    
    public TaskDocument(String title, String description, Date dueDate, String status, Object priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = TaskPriority.resolve(priority);
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public TaskPriority getPriority() {
        return priority;
    }
    
    public void setPriority(Object priority) {
        this.priority = TaskPriority.resolve(priority);
    }
    
    public List<CommentDocument> getComments() {
        return comments;
    }
    
    public void setComments(List<CommentDocument> comments) {
        this.comments = comments;
    }
    
    public void update(TaskDocument updateTask) {
        this.title = updateTask.title;
        this.description = updateTask.description;
        this.dueDate = updateTask.dueDate;
        this.priority = updateTask.priority;
        this.status = updateTask.status;
    }
}
