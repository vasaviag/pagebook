package com.pagebook.pcr.controller;

import com.pagebook.pcr.dto.PostDTO;
import com.pagebook.pcr.dto.PostDetails;
import com.pagebook.pcr.entity.Post;
import com.pagebook.pcr.services.IPostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping (value = "pb/post")
public class PostController {

    @Autowired
    IPostServices iPostServices;

    @PostMapping(value = "/addPost")
    Post addPost(@RequestBody Post post)
    {
        return iPostServices.save(post);
    }

    @PostMapping(value = "/updatePost")
    Post updatePost(@RequestBody Post post)
    {
        return iPostServices.save(post);
    }

    @DeleteMapping(value = "/deletePost/{postId}")
    void deletePost(@PathVariable("postId") int postId)
    {
        iPostServices.deleteById(postId);
    }

    @GetMapping(value = "/getUserPosts/{userId}")
    List<Post> getUserPosts(@PathVariable("userId") String userId)
    {
        return iPostServices.findPostsByUserId(userId);
    }

    @GetMapping(value = "/getPostDetails/{postId}")
    PostDetails getPostDetails(@PathVariable("postId") int postId)
    {
        return iPostServices.findByPostId(postId);
    }

    @GetMapping(value = "/getFriendPosts/{userId}")
    List<PostDTO> getFriendPosts(@PathVariable("userId") String userId)
    {
        return iPostServices.findFriendPosts(userId);
    }
}
