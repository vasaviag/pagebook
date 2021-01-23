package com.pagebook.pcr.dto;

import com.pagebook.pcr.entity.Comment;

import java.util.List;

public class ParentAndChildCommentDTO {
    private int commentId; // (Pk) (Auto Generated)
    private int postId;
    private User user;
    private String commentText;
    List<CommentDTO> comments;

    public ParentAndChildCommentDTO() {
    }

    public ParentAndChildCommentDTO(int commentId, int postId, User user, String commentText, List<CommentDTO> comments) {
        this.commentId = commentId;
        this.postId = postId;
        this.user = user;
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

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
