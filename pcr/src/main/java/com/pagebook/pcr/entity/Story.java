package com.pagebook.pcr.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "story")
public class Story {
    @Id
    private int storyId; //(Pk) (Auto Generated)
    private String userId;
    private String storyUrl;
    private int timestamp;

    public Story() {
        
    }

    public Story(int storyId, String userId, String storyUrl, int timestamp) {
        this.storyId = storyId;
        this.userId = userId;
        this.storyUrl = storyUrl;
        this.timestamp = timestamp;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(String storyUrl) {
        this.storyUrl = storyUrl;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
