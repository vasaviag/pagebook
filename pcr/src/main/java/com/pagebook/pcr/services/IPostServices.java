package com.pagebook.pcr.services;

import com.pagebook.pcr.entity.Post;

import java.util.List;

public interface IPostServices {
    Post save(Post post);
    void deleteById(int id);
    Post findById(int id);
    List<Post> findPostsByUserId(String id);

}
