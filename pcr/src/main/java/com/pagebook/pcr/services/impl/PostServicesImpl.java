package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.entity.Post;
import com.pagebook.pcr.repository.IPostRepository;
import com.pagebook.pcr.services.IPostServices;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServicesImpl implements IPostServices {

    @Autowired
    IPostRepository iPostRepository;

    public Post save(Post post)
    {
        return iPostRepository.save(post);
    }

    public void deleteById(int id)
    {
        iPostRepository.deleteById(id);
    }

    public Post findById(int id)
    {
        return iPostRepository.findById(id).get();
    }

    public List<Post> findPostsByUserId(String id)
    {
        Iterable<Post> postsIterable = iPostRepository.findByUserId(id);
        List<Post> posts = new ArrayList<>();
        postsIterable.forEach(posts::add);
        return posts;
    }

    public Post findByPostId(int id)
    {
        return iPostRepository.findByPostId(id);
    }

}
