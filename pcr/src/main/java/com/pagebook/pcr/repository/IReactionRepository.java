package com.pagebook.pcr.repository;

import com.pagebook.pcr.entity.ReactionOnPosts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IReactionRepository extends CrudRepository<ReactionOnPosts, Integer> {
    List<ReactionOnPosts> findByPostIdAndReactionType(int postId, int reactionType);
}
