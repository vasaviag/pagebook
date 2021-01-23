package com.pagebook.pcr.dto;

import java.util.List;

public class ReactionDetails {
    int reactionType;
    List<User> users;

    public ReactionDetails() {
    }

    public ReactionDetails(int reactionType, List<User> users) {
        this.reactionType = reactionType;
        this.users = users;
    }

    public int getReactionType() {
        return reactionType;
    }

    public void setReactionType(int reactionType) {
        this.reactionType = reactionType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
