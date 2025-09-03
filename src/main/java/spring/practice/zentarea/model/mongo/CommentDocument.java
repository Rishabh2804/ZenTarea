package spring.practice.zentarea.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import spring.practice.zentarea.model.BaseEntity;

import java.util.Date;

@Document(collection = "comments")
@SuppressWarnings("unused")
public final class CommentDocument extends BaseEntity {
    
    @Id
    private String id;
    
    private String taskId;
    private String comment;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    
    public CommentDocument() {
    }
    
    public CommentDocument(@NonNull String taskId, String commentText) {
        this.taskId = taskId;
        this.comment = commentText;
        this.date = new Date();
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTaskId() {
        return taskId;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void update(@NonNull CommentDocument comment) {
        this.comment = comment.getComment();
        this.date = new Date();
    }
}
