package com.pagebook.pcr.entity;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
public class ReactionOnPosts {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int reactionId; // (Primary Key)
    private int postId;
    private String userId;
    private int reactionType; // [ 1 -> like, 2 -> dislike, 3 -> angry, 4 -> heart emoji ]

    public ReactionOnPosts() {
    }

    public ReactionOnPosts(int reactionId, int postId, String userId, int reactionType) {
        this.reactionId = reactionId;
        this.postId = postId;
        this.userId = userId;
        this.reactionType = reactionType;
    }

    public int getReactionId() {
        return reactionId;
    }

    public void setReactionId(int reactionId) {
        this.reactionId = reactionId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getReactionType() {
        return reactionType;
    }

    public void setReactionType(int reactionType) {
        this.reactionType = reactionType;
    }
}
