package com.pagebook.pcr.dto;

import java.util.Date;

public class StoryDTO {

    private int storyId; //(Pk) (Auto Generated)
    private User user;
    private String storyUrl;
    private Date timestamp;

    public StoryDTO() {
    }

    public StoryDTO(int storyId, User user, String storyUrl, Date timestamp) {
        this.storyId = storyId;
        this.user = user;
        this.storyUrl = storyUrl;
        this.timestamp = timestamp;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(String storyUrl) {
        this.storyUrl = storyUrl;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
