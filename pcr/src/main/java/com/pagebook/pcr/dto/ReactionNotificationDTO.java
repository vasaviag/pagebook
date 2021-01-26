package com.pagebook.pcr.dto;

import com.pagebook.pcr.entity.Post;

public class ReactionNotificationDTO {
    private int reactionId; // (Pk) (Auto Generated)
    private Post post;
    private String reactorId;

    public int getReactionId() {
        return reactionId;
    }

    public void setReactionId(int reactionId) {
        this.reactionId = reactionId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getReactor() {
        return reactorId;
    }

    public void setReactor(String reactorId) {
        this.reactorId = reactorId;
    }
}
