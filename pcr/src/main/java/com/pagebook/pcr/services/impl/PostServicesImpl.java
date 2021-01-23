package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.*;
import com.pagebook.pcr.entity.Post;
import com.pagebook.pcr.repository.IPostRepository;
import com.pagebook.pcr.services.ICommentServices;
import com.pagebook.pcr.services.IPostServices;
import com.pagebook.pcr.services.IReactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PostServicesImpl implements IPostServices {

    @Autowired
    IPostRepository iPostRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IReactionServices iReactionServices;

    @Autowired
    ICommentServices iCommentServices;

    public Post save(Post post)
    {
        post.setTimestamp(new Date());
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

    public PostDetails findByPostId(int postId)
    {
        Post post = iPostRepository.findByPostId(postId);
        String uri = "http://10.177.1.179:7081/pb/user/getUserInfo/" + post.getUserId();
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(uri, User.class);
        User user = responseEntity.getBody();
        PostDTO postDTO = new PostDTO(post.getPostId(), user, post.getPostText(), post.getPostUrl(), post.getPostType(), post.getPostCategory(), post.getTimestamp(), post.getSharedPostId());
        List<ReactionsDTO> reactionsDTOS = iReactionServices.findByPostId(postId);
        List<ParentAndChildCommentDTO> parentAndChildCommentDTOS =  iCommentServices.findByPostId(postId);
        PostDetails postDetails =  new PostDetails(postDTO, parentAndChildCommentDTOS, reactionsDTOS);
        return postDetails;
    }

    public List<PostDTO> findFriendPosts(String id)
    {
        String uri = "http://10.177.1.179:7081/pb/user/getFriends/" + id;
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(uri, User[].class);
        User users1 [] = responseEntity.getBody();
        System.out.println(users1);

        List<User> users = Arrays.asList(users1);

        List<PostDTO> postDTOS = new ArrayList<>();
        for (User user : users) {
            Iterable<Post> postsIterable = findPostsByUserId(user.getUserId());
            List<Post> posts = new ArrayList<>();
            postsIterable.forEach(posts::add);

            for (Post post : posts) {
                postDTOS.add(new PostDTO(post.getPostId(), user, post.getPostText(), post.getPostUrl(), post.getPostType(), post.getPostCategory(), post.getTimestamp(), post.getSharedPostId()));
            }
        }
        postDTOS.sort(Comparator.comparing(PostDTO::getTimestamp));

        return postDTOS;
    }

}
