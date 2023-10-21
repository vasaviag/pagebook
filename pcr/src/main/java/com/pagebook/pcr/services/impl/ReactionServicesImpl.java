package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.*;
import com.pagebook.pcr.entity.Post;
import com.pagebook.pcr.entity.ReactionOnPosts;
import com.pagebook.pcr.repository.IReactionRepository;
import com.pagebook.pcr.services.IPostServices;
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
    IPostServices iPostServices;

    @Autowired
    RestTemplateImpl restTemplateImpl;

    public List<ReactionsDTO> save(ReactionRequestDTO reactionRequestDTO)
    {
        ReactionOnPosts reactionOnPosts = iReactionRepository.findByPostIdAndUserId(reactionRequestDTO.getPostId(), reactionRequestDTO.getUserId());
        if(reactionOnPosts == null)
        {
            reactionOnPosts = new ReactionOnPosts();
        }
        else if(reactionOnPosts.getReactionType() == reactionRequestDTO.getReactionType())
        {
            deleteById(reactionOnPosts.getReactionId());
            //todo : need to inform common infra and analytics on the reaction revert


            return findByPostId(reactionOnPosts.getPostId());
        }
        reactionOnPosts.setPostId(reactionRequestDTO.getPostId());
        reactionOnPosts.setUserId(reactionRequestDTO.getUserId());
        reactionOnPosts.setReactionType(reactionRequestDTO.getReactionType());
        reactionOnPosts = iReactionRepository.save(reactionOnPosts);

        //todo : move this code for sending details to common infra and analytics to two different methods
        ReactionNotificationDTO reactionNotificationDTO = new ReactionNotificationDTO();
        Post post = iPostServices.findById(reactionOnPosts.getPostId());

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(post.getUserId());


        reactionNotificationDTO.setPost(post);
        reactionNotificationDTO.setReactor(reactionRequestDTO.getUserId());
        reactionNotificationDTO.setReactionId(reactionOnPosts.getReactionId());

        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
        analyticsDTO.setPostId(post.getPostId());
        if(reactionOnPosts.getReactionType() == 1 || reactionOnPosts.getReactionType() == 3)
            analyticsDTO.setAction("like");
        else
        {
            analyticsDTO.setAction("dislike");
            FullUserDetail fullUserDetail = restTemplateImpl.getFullUserDetail(0, iPostServices.findByPostId(reactionRequestDTO.getPostId(), reactionRequestDTO.getUserId()).getPostDTO().getUserDTO().getUserName());
            if(fullUserDetail.getType() == 3)
            {
                CRMDTO crmdto = new CRMDTO();
                System.out.println("1 step - > CRM");

                crmdto.setLeadID(reactionRequestDTO.getUserId());
                crmdto.setLeadName(restTemplateImpl.getUserDetails(reactionRequestDTO.getUserId()).getUserName());
                crmdto.setLeadType(3);
                crmdto.setBusinessID(fullUserDetail.getUserId());
                crmdto.setPostID(String.valueOf(reactionOnPosts.getPostId()));

                restTemplateImpl.sendDislikeToCRM(crmdto);
                System.out.println("2 step -> CRM");
            }
        }

        analyticsDTO.setCategoryName(post.getPostCategory());
        if(post.getPostType() == 1)
            analyticsDTO.setType("Text");
        else if(post.getPostType() == 2)
            analyticsDTO.setType("Image");
        else
            analyticsDTO.setType("Video");
        analyticsDTO.setChannelId(0);
        analyticsDTO.setTimeStamp(post.getTimestamp());


        restTemplateImpl.sendToAnalytics(analyticsDTO);

        restTemplateImpl.sendReactionDetailsToCommonInfra(reactionNotificationDTO);

        return findByPostId(reactionOnPosts.getPostId());
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
        System.out.println("In Reaction");
        List<ReactionsDTO> reactions = new ArrayList<>();

        for(int i = 1; i <= 4; i++)
        {
            List<ReactionOnPosts> reactionOnPosts = new ArrayList<>();
            Iterable<ReactionOnPosts> reactionsIterable = iReactionRepository.findByPostIdAndReactionType(postId, i);
            reactionsIterable.forEach(reactionOnPosts::add);
            ReactionsDTO reactionsDTO = new ReactionsDTO();
            reactionsDTO.setReactionType(i);
            reactionsDTO.setCount(reactionOnPosts.size());
            reactions.add(reactionsDTO);
        }
        return reactions;
    }

    public List<ReactionDetailsDTO> findByPostIdAndReactionType(int postId, int reactionType)
    {
        //todo : remove the new list creation .. use the iterable only to navigate the entire list
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
        reactions.add(reactionDetailsDTO);
        return reactions;
    }
}
