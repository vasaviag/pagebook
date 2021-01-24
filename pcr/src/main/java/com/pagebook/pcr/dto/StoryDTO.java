package com.pagebook.pcr.dto;

import java.util.Date;
import java.util.List;

public class StoryDTO {

    private int storyId; //(Pk) (Auto Generated)
    private UserDTO userDTO;
    //todo : phani : support multiple images - (Done)
    private String storyUrl;
    private Date timestamp;

    
    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
