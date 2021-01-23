package com.pagebook.pcr.dto;

import java.util.Date;

public class PostDTO {
    private int postId; // (Pk) (Auto Generated)
    private User user;
    private String postText;
    private String postUrl;
    private int postType; // [ 1 -> text, 2 -> image, 3 -> video ]
    private String postCategory;
    private Date timestamp;
    private int sharedPostId;

    public PostDTO() {
    }

    public PostDTO(int postId, User user, String postText, String postUrl, int postType, String postCategory, Date timestamp, int sharedPostId) {
        this.postId = postId;
        this.user = user;
        this.postText = postText;
        this.postUrl = postUrl;
        this.postType = postType;
        this.postCategory = postCategory;
        this.timestamp = timestamp;
        this.sharedPostId = sharedPostId;
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

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getSharedPostId() {
        return sharedPostId;
    }

    public void setSharedPostId(int sharedPostId) {
        this.sharedPostId = sharedPostId;
    }
}
