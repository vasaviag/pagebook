package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.ReactionDetails;
import com.pagebook.pcr.dto.ReactionsDTO;
import com.pagebook.pcr.dto.User;
import com.pagebook.pcr.entity.ReactionOnPosts;
import com.pagebook.pcr.repository.IReactionRepository;
import com.pagebook.pcr.services.IReactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReactionServicesImpl implements IReactionServices {

    @Autowired
    IReactionRepository iReactionRepository;

    @Autowired
    RestTemplate restTemplate;

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

    public List<ReactionsDTO> findByPostId(int postId)
    {
        List<ReactionsDTO> reactions = new ArrayList<>();

        for(int i = 1; i <= 4; i++)
        {
            List<User> users = new ArrayList<>();
            List<ReactionOnPosts> reactionOnPosts = new ArrayList<>();
            Iterable<ReactionOnPosts> reactionsIterable = iReactionRepository.findByPostIdAndReactionType(postId, i);
            reactionsIterable.forEach(reactionOnPosts::add);
            reactions.add(new ReactionsDTO(i, reactionOnPosts.size()));
        }

        return reactions;
    }

    public List<ReactionDetails> findByPostIdAndReactionType(int postId, int reactionType)
    {
        List<ReactionDetails> reactions = new ArrayList<>();
        List<ReactionOnPosts> reactionOnPosts = new ArrayList<>();
        Iterable<ReactionOnPosts> reactionsIterable = iReactionRepository.findByPostIdAndReactionType(postId, reactionType);
        reactionsIterable.forEach(reactionOnPosts::add);

        List<String> userIds = new ArrayList<>();

        for (ReactionOnPosts reactionOnPost : reactionOnPosts) {
            userIds.add(reactionOnPost.getUserId());
        }
        final String uri = "http://10.177.1.179:7081/pb/user/userDetailsList";
        ResponseEntity<User[]> responseEntity = restTemplate.postForEntity(uri, userIds, User[].class);
        User users1 []  = responseEntity.getBody();
        List<User> users = Arrays.asList(users1);
        reactions.add(new ReactionDetails(reactionType, users));

        return reactions;
    }
}
