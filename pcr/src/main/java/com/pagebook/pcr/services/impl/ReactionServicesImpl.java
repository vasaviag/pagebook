package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.ReactionDetailsDTO;
import com.pagebook.pcr.dto.ReactionRequestDTO;
import com.pagebook.pcr.dto.ReactionsDTO;
import com.pagebook.pcr.dto.UserDTO;
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

    @Autowired
    RestTemplateImpl restTemplateImpl;

    public ReactionOnPosts save(ReactionRequestDTO reactionRequestDTO)
    {
        ReactionOnPosts reactionOnPosts = new ReactionOnPosts();
        reactionOnPosts.setPostId(reactionRequestDTO.getPostId());
        reactionOnPosts.setUserId(reactionRequestDTO.getUserId());
        reactionOnPosts.setReactionType(reactionRequestDTO.getReactionType());

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
            List<UserDTO> userDTOS = new ArrayList<>();
            List<ReactionOnPosts> reactionOnPosts = new ArrayList<>();
            Iterable<ReactionOnPosts> reactionsIterable = iReactionRepository.findByPostIdAndReactionType(postId, i);
            reactionsIterable.forEach(reactionOnPosts::add);
            ReactionsDTO reactionsDTO = new ReactionsDTO();
            reactionsDTO.setReactionType(i);
            reactionsDTO.setCount(reactionOnPosts.size());
            reactions.add(reactionsDTO);
            System.out.println(reactionOnPosts.size());
        }
        return reactions;
    }

    public List<ReactionDetailsDTO> findByPostIdAndReactionType(int postId, int reactionType)
    {
        List<ReactionDetailsDTO> reactions = new ArrayList<>();
        List<ReactionOnPosts> reactionOnPosts = new ArrayList<>();
        Iterable<ReactionOnPosts> reactionsIterable = iReactionRepository.findByPostIdAndReactionType(postId, reactionType);
        reactionsIterable.forEach(reactionOnPosts::add);

        List<String> userIds = new ArrayList<>();

        for (ReactionOnPosts reactionOnPost : reactionOnPosts) {
            userIds.add(reactionOnPost.getUserId());
        }
        List<UserDTO> userDTOS = restTemplateImpl.getUserDetailsList(userIds);
        ReactionDetailsDTO reactionDetailsDTO = new ReactionDetailsDTO();
        reactionDetailsDTO.setReactionType(reactionType);
        reactionDetailsDTO.setUserDTOS(userDTOS);
        return reactions;
    }
}
