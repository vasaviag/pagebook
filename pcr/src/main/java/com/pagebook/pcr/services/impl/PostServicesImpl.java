package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.*;
import com.pagebook.pcr.entity.Post;
import com.pagebook.pcr.entity.ReactionOnPosts;
import com.pagebook.pcr.repository.IPostRepository;
import com.pagebook.pcr.repository.IReactionRepository;
import com.pagebook.pcr.services.ICommentServices;
import com.pagebook.pcr.services.IPostServices;
import com.pagebook.pcr.services.IReactionServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PostServicesImpl implements IPostServices {

    @Autowired
    IPostRepository iPostRepository;

    @Autowired
    IReactionRepository iReactionRepository;

    @Autowired
    IReactionServices iReactionServices;

    @Autowired
    ICommentServices iCommentServices;

    @Autowired
    RestTemplateImpl restTemplateImpl;

    public Post save(PostRequestDTO postRequestDTO)
    {
        //todo : phani : use spring bean utils to copy content from DTO to entity

        postRequestDTO.setTimestamp(new Date());
        Post post = new Post();
        //BeanUtils.copyProperties(postRequestDTO, post);
        post.setTimestamp(postRequestDTO.getTimestamp());
        post.setPostCategory(postRequestDTO.getPostCategory());
        post.setPostText(postRequestDTO.getPostText());
        post.setPostType(postRequestDTO.getPostType());
        post.setPostUrl(postRequestDTO.getPostUrl());
        post.setSharedPostId(postRequestDTO.getSharedPostId());
        post.setUserId(postRequestDTO.getUserId());
        Post post1 = iPostRepository.save(post);
        restTemplateImpl.sendPostDetailsToCommonInfra(post1);

        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
        analyticsDTO.setPostId(post1.getPostId());
        analyticsDTO.setAction("post");
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

        return post1;
    }

    public void deleteById(int id)
    {
        iPostRepository.deleteById(id);
    }

    public Post findById(int id)
    {
        return iPostRepository.findById(id).get();
    }

    public List<PostDetailsDTO> findPostsByUserId(String id)
    {
        Iterable<Post> postsIterable = iPostRepository.findByUserId(id);

        List<PostDetailsDTO> postDetailsDTOS = new ArrayList<>();
        for (Post post : postsIterable)
        {
            postDetailsDTOS.add(findByPostId(post.getPostId(), id));
        }
        return postDetailsDTOS;
    }

    public PostDetailsDTO findByPostId(int postId, String userId)
    {
        Post post = iPostRepository.findById(postId).get();

        ReactionOnPosts reactionOnPosts = iReactionRepository.findByPostIdAndUserId(postId, userId);
        if(reactionOnPosts == null)
        {
            reactionOnPosts = new ReactionOnPosts();
            reactionOnPosts.setReactionType(-1);
        }
        System.out.println("kkkkk "+reactionOnPosts.getReactionType());

        UserDTO userDTO = restTemplateImpl.getUserDetails(post.getUserId());
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getPostId());
        postDTO.setUserDTO(userDTO);
        postDTO.setPostText(post.getPostText());
        postDTO.setPostUrl(post.getPostUrl());
        postDTO.setPostType(post.getPostType());
        postDTO.setPostCategory(post.getPostCategory());
        postDTO.setTimestamp(post.getTimestamp());
        postDTO.setSharedPostId(post.getSharedPostId());

        List<ReactionsDTO> reactionsDTOS = iReactionServices.findByPostId(postId);
        List<ParentAndChildCommentDTO> parentAndChildCommentDTOS =  iCommentServices.findByPostId(postId);
        PostDetailsDTO postDetailsDTO =  new PostDetailsDTO();
        postDetailsDTO.setParentAndChildCommentDTO(parentAndChildCommentDTOS);
        postDetailsDTO.setPostDTO(postDTO);
        postDetailsDTO.setReactionsDTOS(reactionsDTOS);
        postDetailsDTO.setTimestamp(post.getTimestamp());
        System.out.println("herre "+ reactionOnPosts.getReactionType());
        postDetailsDTO.setReactionType(reactionOnPosts.getReactionType());
        return postDetailsDTO;
    }

    public List<PostDetailsDTO> findFriendPosts(String id)
    {
        System.out.println("before rest call");
        List<UserDTO> userDTOS = restTemplateImpl.getFriendsDetails(id);
        System.out.println("after rest call");

        List<PostDetailsDTO> postDetailsDTOS = new ArrayList<>();
        for (UserDTO userDTO : userDTOS) {
            Iterable<PostDetailsDTO> postsIterable = findPostsByUserId(userDTO.getUserId());
            List<PostDetailsDTO> posts = new ArrayList<>();
            postsIterable.forEach(posts::add);
            postDetailsDTOS.addAll(posts);
        }
        postDetailsDTOS.sort(Comparator.comparing(PostDetailsDTO::getTimestamp).reversed());

        return postDetailsDTOS;
    }

}
