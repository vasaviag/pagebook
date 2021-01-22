package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.Reactions;
import com.pagebook.pcr.dto.User;
import com.pagebook.pcr.entity.ReactionOnPosts;
import com.pagebook.pcr.repository.IReactionRepository;
import com.pagebook.pcr.services.IReactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReactionServicesImpl implements IReactionServices {

    @Autowired
    IReactionRepository iReactionRepository;

    public ReactionOnPosts save(ReactionOnPosts reactionOnPosts)
    {
        return iReactionRepository.save(reactionOnPosts);
    }

    public void deleteById(int id)
    {
        iReactionRepository.deleteById(id);
    }

    public ReactionOnPosts findById(int id)
    {
        return iReactionRepository.findById(id).get();
    }

    public List<Reactions> findByPostId(int postId)
    {
        List<Reactions> reactions = new ArrayList<>();

        for(int i = 1; i <= 4; i++)
        {
            List<User> users = new ArrayList<>();
            List<ReactionOnPosts> reactionOnPosts = new ArrayList<>();
            Iterable<ReactionOnPosts> reactionsIterable = iReactionRepository.findByPostIdAndReactionType(postId, i);
            reactionsIterable.forEach(reactionOnPosts::add);
            for (ReactionOnPosts reaction : reactionOnPosts)
            {
                User user = new User();
                user.setUserId(reaction.getUserId());
                users.add(user);
            }
            reactions.add(new Reactions(i, reactionOnPosts.size(),users));
        }

        return reactions;
    }
}
