package com.pagebook.pcr.dto;

import java.util.Date;
import java.util.List;

public class PostDetails {
    private PostDTO postDTO;
    private List<ParentAndChildCommentDTO> parentAndChildCommentDTO;
    private List<ReactionsDTO> reactionsDTOS;

    public PostDetails() {
    }

    public PostDetails(PostDTO postDTO, List<ParentAndChildCommentDTO> parentAndChildCommentDTO, List<ReactionsDTO> reactionsDTOS) {
        this.postDTO = postDTO;
        this.parentAndChildCommentDTO = parentAndChildCommentDTO;
        this.reactionsDTOS = reactionsDTOS;
    }

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
}
