package com.pagebook.pcr.dto;

public class ReactionNotificationDTO {
    private int reactionId; // (Pk) (Auto Generated)
    private PostDTO postDTO;
    private String reactorId;

    public int getReactionId() {
        return reactionId;
    }

    public void setReactionId(int reactionId) {
        this.reactionId = reactionId;
    }

    public PostDTO getPostDTO() {
        return postDTO;
    }

    public void setPostDTO(PostDTO postDTO) {
        this.postDTO = postDTO;
    }

    public String getReactor() {
        return reactorId;
    }

    public void setReactor(String reactorId) {
        this.reactorId = reactorId;
    }
}
