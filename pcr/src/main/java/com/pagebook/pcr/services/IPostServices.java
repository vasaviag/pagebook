package com.pagebook.pcr.services;

import com.pagebook.pcr.dto.PostDTO;
import com.pagebook.pcr.dto.PostDetails;
import com.pagebook.pcr.entity.Post;

import java.util.List;

public interface IPostServices {
    Post save(Post post);
    void deleteById(int id);
    Post findById(int id);
    PostDetails findByPostId(int postId);
    List<Post> findPostsByUserId(String id);
    List<PostDTO> findFriendPosts(String userId);

}
