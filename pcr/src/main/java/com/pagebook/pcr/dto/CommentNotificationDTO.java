package com.pagebook.pcr.dto;

import com.pagebook.pcr.entity.Post;

public class CommentNotificationDTO {
    private int commentId; // (Pk) (Auto Generated)
    private Post post;
    private String commentorId;
    private String commentText;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getCommentorId() {
        return commentorId;
    }

    public void setCommentorId(String commentorId) {
        this.commentorId = commentorId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
