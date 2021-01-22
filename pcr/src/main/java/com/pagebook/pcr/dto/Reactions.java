package com.pagebook.pcr.dto;

import com.pagebook.pcr.entity.ReactionOnPosts;

import java.util.List;

public class Reactions {
    int reactionId;
    int count;
    List <User> users;

    public Reactions() {
    }

    public Reactions(int reactionId, int count, List<User> users) {
        this.reactionId = reactionId;
        this.count = count;
        this.users = users;
    }

    public int getReactionId() {
        return reactionId;
    }

    public void setReactionId(int reactionId) {
        this.reactionId = reactionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
