package spring.practice.zentarea.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
public final class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmtID;

    private final Long taskID;
    @Lob
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Comment() {
        this.taskID = null;
    }

    public Comment(@NonNull Long taskID) {
        this.taskID = taskID;
    }

    public Comment(@NonNull Long taskID, String commentText) {
        this.taskID = taskID;
        this.comment = commentText;
        this.date = new Date();
    }
}
