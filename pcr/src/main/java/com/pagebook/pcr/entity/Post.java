package com.pagebook.pcr.entity;

import javax.persistence.*;

@Entity
@Table(name = "posts")

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId; // (Pk) (Auto Generated)
    private String userId;
    private String postText;
    private String postUrl;
    private int postType; // [ 1 -> text, 2 -> image, 3 -> video ]
    private String postCategory;
    private int timestamp;
    private int sharedPostId;

    public Post() {
    }

    public Post(int postId, String userId, String postText, String postUrl, int postType, String postCategory, int timestamp, int sharedPostId) {
        this.postId = postId;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getSharedPostId() {
        return sharedPostId;
    }

    public void setSharedPostId(int sharedPostId) {
        this.sharedPostId = sharedPostId;
    }
}
