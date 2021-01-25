package com.pagebook.pcr.dto;

import java.util.Date;

public class PostDTO {
    private int postId; // (Pk) (Auto Generated)
    private UserDTO userDTO;
    private String postText;
    private String postUrl;
    private int postType; // [ 1 -> text, 2 -> image, 3 -> video ]
    private String postCategory;
    private Date timestamp;
    private int sharedPostId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
