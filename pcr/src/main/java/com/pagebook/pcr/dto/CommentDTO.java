package com.pagebook.pcr.dto;

import com.pagebook.pcr.entity.Comment;

import java.util.List;

public class CommentDTO {
    private int commentId; // (Pk) (Auto Generated)
    private int postId;
    private User user;
    private String commentText;

    public CommentDTO() {
    }

    public CommentDTO(int commentId, int postId, User user, String commentText) {
        this.commentId = commentId;
        this.postId = postId;
        this.user = user;
        this.commentText = commentText;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
