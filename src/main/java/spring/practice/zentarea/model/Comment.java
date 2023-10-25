package spring.practice.zentarea.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@Table(name = "comment")
public final class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_id")
    private Long cmtId;

    public Long getCmtId() {
        return cmtId;
    }

    @Column(name = "task_id")
    private final Long taskId;

    public Long getTaskId() {
        return taskId;
    }

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

    public Comment() {
        this.taskId = null;
    }

    public Comment(@NonNull Long taskId, String commentText) {
        this.taskId = taskId;
        this.comment = commentText;
        this.date = new Date();
    }
}
