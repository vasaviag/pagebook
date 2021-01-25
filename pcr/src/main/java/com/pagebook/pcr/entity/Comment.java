package com.pagebook.pcr.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId; // (Pk) (Auto Generated)
    private int postId;
    private String userId;
    private String commentText;
    private int parentCommentId;
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
}
