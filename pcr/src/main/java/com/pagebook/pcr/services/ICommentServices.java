package com.pagebook.pcr.services;

import com.pagebook.pcr.dto.ParentAndChildComment;
import com.pagebook.pcr.entity.Comment;

import java.util.List;

public interface ICommentServices {

    Comment save(Comment comment);
    void deleteById(int id);
    Comment findById(int id);
    List<ParentAndChildComment> findByPostId(int postId);
    List<Comment> findAll();
}