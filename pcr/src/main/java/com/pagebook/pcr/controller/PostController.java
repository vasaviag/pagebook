package com.pagebook.pcr.controller;

import com.pagebook.pcr.dto.PostDTO;
import com.pagebook.pcr.dto.PostDetailsDTO;
import com.pagebook.pcr.dto.PostRequestDTO;
import com.pagebook.pcr.entity.Post;
import com.pagebook.pcr.services.IPostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping (value = "pb/post")
//todo : /addPost and /updatePost /deletePost URN to remove from the code and just use post and put mapping instead
public class PostController {

    @Autowired
    IPostServices iPostServices;

    @PostMapping(value = "/addPost")
    Post addPost(@RequestBody PostRequestDTO postRequestDTO)
    {
        return iPostServices.save(postRequestDTO);
    }

    //todo : change this to putmapping
    @PostMapping(value = "/updatePost")
    Post updatePost(@RequestBody PostRequestDTO postRequestDTO)
    {
        return iPostServices.save(postRequestDTO);
    }

    @DeleteMapping(value = "/deletePost/{postId}")
    void deletePost(@PathVariable("postId") int postId)
    {
        iPostServices.deleteById(postId);
    }

    @GetMapping(value = "/getUserPosts/{userId}")
    List<PostDetailsDTO> getUserPosts(@PathVariable("userId") String userId)
    {
        return iPostServices.findPostsByUserId(userId);
    }

    @GetMapping(value = "/getPostDetails/{postId}/{userId}")
    PostDetailsDTO getPostDetails(@PathVariable("postId") int postId, @PathVariable("userId") String userId)
    {
        return iPostServices.findByPostId(postId, userId);
    }

    @GetMapping(value = "/getFriendPosts/{userId}")
    List<PostDetailsDTO> getFriendPosts(@PathVariable("userId") String userId)
    {
        return iPostServices.findFriendPosts(userId);
    }
}
