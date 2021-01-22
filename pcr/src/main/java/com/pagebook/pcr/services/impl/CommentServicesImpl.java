package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.ParentAndChildComment;
import com.pagebook.pcr.entity.Comment;
import com.pagebook.pcr.repository.ICommentRepository;
import com.pagebook.pcr.services.ICommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServicesImpl implements ICommentServices {
    @Autowired
    ICommentRepository iCommentRepository;
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

    public List<ParentAndChildComment> findByPostId(int postId)
    {
        List<ParentAndChildComment> parentAndChildComments = new ArrayList<>();
        Iterable<Comment> commentsIterable = iCommentRepository.findByPostIdAndParentCommentId(postId, 0);
        List<Comment> comments = new ArrayList<>();
        commentsIterable.forEach(comments::add);
        for (Comment comment: comments)
        {
            Iterable<Comment> commentsIterableChild = iCommentRepository.findByPostIdAndParentCommentId(postId, comment.getCommentId());
            List<Comment> childComments = new ArrayList<>();
            commentsIterableChild.forEach(childComments::add);
            System.out.println(childComments.toString());
            parentAndChildComments.add(new ParentAndChildComment(comment.getCommentId(), comment.getPostId(), comment.getUserId(), comment.getCommentText(), childComments));
        }
        return parentAndChildComments;
    }

    public List<Comment> findAll()
    {
        Iterable<Comment> commentsIterableChild = iCommentRepository.findAll();
        List<Comment> childComments = new ArrayList<>();
        commentsIterableChild.forEach(childComments::add);
        return childComments;
    }

}
