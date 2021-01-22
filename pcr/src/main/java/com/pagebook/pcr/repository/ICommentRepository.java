package com.pagebook.pcr.repository;

import com.pagebook.pcr.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICommentRepository extends CrudRepository<Comment, Integer> {
    List <Comment> findByPostIdAndParentCommentId(Integer postId, Integer commentId);
}
