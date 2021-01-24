package com.pagebook.pcr.services;

import com.pagebook.pcr.dto.PostDTO;
import com.pagebook.pcr.dto.PostDetailsDTO;
import com.pagebook.pcr.dto.PostRequestDTO;
import com.pagebook.pcr.entity.Post;

import java.util.List;

public interface IPostServices {
    Post save(PostRequestDTO postRequestDTO);
    void deleteById(int id);
    Post findById(int id);
    PostDetailsDTO findByPostId(int postId);
    List<Post> findPostsByUserId(String id);
    List<PostDTO> findFriendPosts(String userId);

}
