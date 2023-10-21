package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.*;
import com.pagebook.pcr.entity.Comment;
import com.pagebook.pcr.entity.Post;
import com.pagebook.pcr.repository.ICommentRepository;
import com.pagebook.pcr.services.ICommentServices;
import com.pagebook.pcr.services.IPostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServicesImpl implements ICommentServices {
    @Autowired
    ICommentRepository iCommentRepository;

    @Autowired
    RestTemplateImpl restTemplateImpl;

    @Autowired
    IPostServices iPostServices;

    public Comment save(CommentRequestDTO commentRequestDTO)
    {
        Comment comment = new Comment();
        comment.setUserId(commentRequestDTO.getUserId());
        comment.setPostId(commentRequestDTO.getPostId());
        comment.setCommentText(commentRequestDTO.getCommentText());
        comment.setParentCommentId(commentRequestDTO.getParentCommentId());
        comment.setTimestamp(new Date());

        Comment comment1 = iCommentRepository.save(comment);

        CommentNotificationDTO commentNotificationDTO = new CommentNotificationDTO();
        commentNotificationDTO.setCommentorId(commentRequestDTO.getUserId());
        commentNotificationDTO.setCommentId(comment1.getCommentId());
        commentNotificationDTO.setCommentText(comment1.getCommentText());
        Post post = iPostServices.findById(comment.getPostId());

        commentNotificationDTO.setPost(post);

        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
        analyticsDTO.setPostId(post.getPostId());
        analyticsDTO.setAction("comment");
        analyticsDTO.setCategoryName(post.getPostCategory());
        if(post.getPostType() == 1)
            analyticsDTO.setType("Text");
        else if(post.getPostType() == 2)
            analyticsDTO.setType("Image");
        else
            analyticsDTO.setType("Video");
        analyticsDTO.setChannelId(0);
        analyticsDTO.setTimeStamp(post.getTimestamp());

        restTemplateImpl.sendCommentDetailsToCommonInfra(commentNotificationDTO);

        return comment1;
    }

    public void deleteById(int id)
    {
        iCommentRepository.deleteById(id);
    }

    public Comment findById(int id)
    {
        return iCommentRepository.findById(id).get();
    }

    public List<ParentAndChildCommentDTO> findByPostId(int postId)
    {
        List<ParentAndChildCommentDTO> parentAndChildCommentDTOS = new ArrayList<>();
        Iterable<Comment> commentsIterable = iCommentRepository.findByPostIdAndParentCommentId(postId, 0);
        for (Comment comment: commentsIterable)
        {
            System.out.println("In Comment");
            List<CommentDTO> commentDTOS = new ArrayList<>();

            UserDTO userDTO = restTemplateImpl.getUserDetails(comment.getUserId());

            Iterable<Comment> commentsIterableChild = iCommentRepository.findByPostIdAndParentCommentId(postId, comment.getCommentId());
            List<Comment> childComments = new ArrayList<>();
            commentsIterableChild.forEach(childComments::add);


            for (Comment comment1 : childComments)
            {
                UserDTO userDTO1 = restTemplateImpl.getUserDetails(comment1.getUserId());
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentId(comment1.getCommentId());
                commentDTO.setPostId(comment1.getPostId());
                commentDTO.setCommentText(comment1.getCommentText());
                commentDTO.setUserDTO(userDTO1);
                commentDTOS.add(commentDTO);
            }
            ParentAndChildCommentDTO parentAndChildCommentDTO = new ParentAndChildCommentDTO();
            parentAndChildCommentDTO.setCommentId(comment.getCommentId());
            parentAndChildCommentDTO.setComments(commentDTOS);
            parentAndChildCommentDTO.setCommentText(comment.getCommentText());
            parentAndChildCommentDTO.setPostId(comment.getPostId());
            parentAndChildCommentDTO.setUserDTO(userDTO);

            parentAndChildCommentDTOS.add(parentAndChildCommentDTO);
        }
        return parentAndChildCommentDTOS;
    }

    public List<Comment> findAll()
    {
        Iterable<Comment> commentsIterableChild = iCommentRepository.findAll();
        List<Comment> childComments = new ArrayList<>();
        commentsIterableChild.forEach(childComments::add);
        return childComments;
    }

}
