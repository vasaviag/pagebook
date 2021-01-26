package com.pagebook.pcr.services;

import com.pagebook.pcr.dto.ReactionDetailsDTO;
import com.pagebook.pcr.dto.ReactionRequestDTO;
import com.pagebook.pcr.dto.ReactionsDTO;
import com.pagebook.pcr.entity.ReactionOnPosts;

import java.util.List;

public interface IReactionServices{
    List<ReactionsDTO> save(ReactionRequestDTO reactionRequestDTO);
    void deleteById(int id);
    ReactionOnPosts findById(int id);
    List<ReactionsDTO> findByPostId(int postId);
    List<ReactionDetailsDTO> findByPostIdAndReactionType(int postId, int reactionType);

}
