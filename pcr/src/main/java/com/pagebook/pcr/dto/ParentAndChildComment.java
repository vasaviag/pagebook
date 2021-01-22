package com.pagebook.pcr.dto;

import com.pagebook.pcr.entity.Comment;

import java.util.List;

public class ParentAndChildComment {
    private int commentId; // (Pk) (Auto Generated)
    private int postId;
    private int userId;
    private String commentText;
    List<Comment> comments;

    public ParentAndChildComment() {
    }

    public ParentAndChildComment(int commentId, int postId, int userId, String commentText, List<Comment> comments) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
        this.comments = comments;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
