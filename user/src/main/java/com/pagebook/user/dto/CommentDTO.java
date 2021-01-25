package com.pagebook.user.dto;

import com.pagebook.user.entity.User;

public class CommentDTO {

    private int commentId; // (Pk) (Auto Generated)
    private int postId;
    private User user;
    private String commentText;


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

    public User getUserDTO() {
        return user;
    }

    public void setUserDTO(User user) {
        this.user = user;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
