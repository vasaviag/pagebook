package com.pagebook.pcr.dto;

import com.pagebook.pcr.entity.ReactionOnPosts;

import java.util.List;

public class ReactionsDTO {
    int reactionType;
    int count;

    public ReactionsDTO() {
    }

    public ReactionsDTO(int reactionType, int count) {
        this.reactionType = reactionType;
        this.count = count;
    }

    public int getReactionType() {
        return reactionType;
    }

    public void setReactionType(int reactionType) {
        this.reactionType = reactionType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
