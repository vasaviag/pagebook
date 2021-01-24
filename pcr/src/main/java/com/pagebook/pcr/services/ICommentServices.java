package com.pagebook.pcr.services;

import com.pagebook.pcr.dto.CommentDTO;
import com.pagebook.pcr.dto.CommentRequestDTO;
import com.pagebook.pcr.dto.ParentAndChildCommentDTO;
import com.pagebook.pcr.entity.Comment;

import java.util.List;

public interface ICommentServices {

    Comment save(CommentRequestDTO commentRequestDTO);
    void deleteById(int id);
    Comment findById(int id);
    List<ParentAndChildCommentDTO> findByPostId(int postId);
    List<Comment> findAll();
}
