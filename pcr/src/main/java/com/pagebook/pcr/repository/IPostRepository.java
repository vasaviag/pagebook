package com.pagebook.pcr.repository;

import com.pagebook.pcr.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPostRepository extends CrudRepository<Post, Integer> {
    List<Post> findByUserId(String id);
    Post findByPostId(int id);
}
