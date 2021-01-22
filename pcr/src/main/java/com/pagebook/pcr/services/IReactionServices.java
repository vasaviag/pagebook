package com.pagebook.pcr.services;

import com.pagebook.pcr.dto.Reactions;
import com.pagebook.pcr.entity.ReactionOnPosts;

import java.util.List;

public interface IReactionServices{
    ReactionOnPosts save(ReactionOnPosts reactionOnPosts);
    void deleteById(int id);
    ReactionOnPosts findById(int id);
    List<Reactions> findByPostId(int postId);
}
