package com.pagebook.pcr.services;

import com.pagebook.pcr.dto.ReactionDetails;
import com.pagebook.pcr.dto.ReactionsDTO;
import com.pagebook.pcr.entity.ReactionOnPosts;

import java.util.List;

public interface IReactionServices{
    ReactionOnPosts save(ReactionOnPosts reactionOnPosts);
    void deleteById(int id);
    ReactionOnPosts findById(int id);
    List<ReactionsDTO> findByPostId(int postId);
    List<ReactionDetails> findByPostIdAndReactionType(int postId, int reactionType);
}
