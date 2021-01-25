package com.pagebook.pcr.dto;

import java.util.Date;
import java.util.List;

public class StoryRequestDTO {

    private String userId;
    private List<String> storyUrl;
    private Date timestamp;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(List<String> storyUrl) {
        this.storyUrl = storyUrl;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
