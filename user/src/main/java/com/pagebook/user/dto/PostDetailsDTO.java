package com.pagebook.user.dto;

import java.util.Date;
import java.util.List;

public class PostDetailsDTO {

    private PostDTO postDTO;
    private List<ParentAndChildCommentDTO> parentAndChildCommentDTO;
    private List<ReactionsDTO> reactionsDTOS;
    private Date timestamp;
    private int reactionType;
    public PostDTO getPostDTO() {
        return postDTO;
    }

    public void setPostDTO(PostDTO postDTO) {
        this.postDTO = postDTO;
    }

    public List<ParentAndChildCommentDTO> getParentAndChildCommentDTO() {
        return parentAndChildCommentDTO;
    }

    public void setParentAndChildCommentDTO(List<ParentAndChildCommentDTO> parentAndChildCommentDTO) {
        this.parentAndChildCommentDTO = parentAndChildCommentDTO;
    }

    public List<ReactionsDTO> getReactionsDTOS() {
        return reactionsDTOS;
    }

    public void setReactionsDTOS(List<ReactionsDTO> reactionsDTOS) {
        this.reactionsDTOS = reactionsDTOS;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getReactionType() {
        return reactionType;
    }

    public void setReactionType(int reactionType) {
        this.reactionType = reactionType;
    }
}
