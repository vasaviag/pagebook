package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.CommentDTO;
import com.pagebook.pcr.dto.ParentAndChildCommentDTO;
import com.pagebook.pcr.dto.User;
import com.pagebook.pcr.entity.Comment;
import com.pagebook.pcr.repository.ICommentRepository;
import com.pagebook.pcr.services.ICommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServicesImpl implements ICommentServices {
    @Autowired
    ICommentRepository iCommentRepository;

    @Autowired
    RestTemplate restTemplate;
    public Comment save(Comment comment)
    {
        return iCommentRepository.save(comment);
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
        List<Comment> comments = new ArrayList<>();
        commentsIterable.forEach(comments::add);
        for (Comment comment: comments)
        {
            List<CommentDTO> commentDTOS = new ArrayList<>();

            String uri = "http://10.177.1.179:7081/pb/user/getUserInfo/" + comment.getUserId();
            ResponseEntity<User> responseEntity = restTemplate.getForEntity(uri, User.class);
            User user = responseEntity.getBody();

            Iterable<Comment> commentsIterableChild = iCommentRepository.findByPostIdAndParentCommentId(postId, comment.getCommentId());
            List<Comment> childComments = new ArrayList<>();
            commentsIterableChild.forEach(childComments::add);


            for (Comment comment1 : childComments)
            {
                String uri1 = "http://10.177.1.179:7081/pb/user/getUserInfo/" + comment1.getUserId();
                ResponseEntity<User> responseEntity1 = restTemplate.getForEntity(uri1, User.class);
                User user1 = responseEntity1.getBody();
                commentDTOS.add(new CommentDTO(comment1.getCommentId(), comment1.getPostId(), user1, comment1.getCommentText()));
            }

            parentAndChildCommentDTOS.add(new ParentAndChildCommentDTO(comment.getCommentId(), comment.getPostId(), user, comment.getCommentText(), commentDTOS));
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
