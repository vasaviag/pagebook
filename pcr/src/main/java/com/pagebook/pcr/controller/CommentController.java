package com.pagebook.pcr.controller;

import com.pagebook.pcr.dto.CommentRequestDTO;
import com.pagebook.pcr.dto.ParentAndChildCommentDTO;
import com.pagebook.pcr.entity.Comment;
import com.pagebook.pcr.services.ICommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "pb/comment")
//todo  : phani : take input as DTO and convert into entities if required to be saved or updated or deleted
public class CommentController {
    @Autowired
    ICommentServices iCommentServices;

    @PostMapping(value = "/addComment")
    Comment addComment(@RequestBody CommentRequestDTO comment)
    {
        return iCommentServices.save(comment);
    }

    @PostMapping(value = "/updateComment")
    Comment updateComment(@RequestBody CommentRequestDTO comment)
    {
        return iCommentServices.save(comment);
    }

    @DeleteMapping(value = "/deleteComment/{commentId}")
    void deleteComment(@PathVariable("commentId") int commentId)
    {
        iCommentServices.deleteById(commentId);
    }

    @GetMapping(value = "/getComment/{commentId}")
    Comment getComment(@PathVariable("commentId") int commentId)
    {
        return iCommentServices.findById(commentId);
    }

    @GetMapping(value = "/getComments/{postId}")
    List<ParentAndChildCommentDTO> getPostComments(@PathVariable("postId") int postId)
    {
        return iCommentServices.findByPostId(postId);
    }

    @GetMapping(value = "/findAll")
    List<Comment> findAll()
    {
        return iCommentServices.findAll();
    }
}
