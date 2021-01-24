package com.pagebook.pcr.dto;

import java.util.List;

public class ReactionDetailsDTO {
    int reactionType;
    List<UserDTO> userDTOS;


    public int getReactionType() {
        return reactionType;
    }

    public void setReactionType(int reactionType) {
        this.reactionType = reactionType;
    }

    public List<UserDTO> getUserDTOS() {
        return userDTOS;
    }

    public void setUserDTOS(List<UserDTO> userDTOS) {
        this.userDTOS = userDTOS;
    }
}
