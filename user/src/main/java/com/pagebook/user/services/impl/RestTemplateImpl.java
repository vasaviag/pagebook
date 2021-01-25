package com.pagebook.user.services.impl;

import com.pagebook.user.dto.FullUserDetail;
import com.pagebook.user.dto.PostDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestTemplateImpl {

    @Autowired
    RestTemplate restTemplate;

    public List<PostDetailsDTO> getUserPosts(String userId) {
        String uri = "http://10.177.1.69:8081/pb/user/getUserPosts/" + userId;
        ResponseEntity<PostDetailsDTO[]> responseEntity = restTemplate.getForEntity(uri, PostDetailsDTO[].class);
        PostDetailsDTO postDetailsDTO[] = responseEntity.getBody();
        List<PostDetailsDTO> postDetailsDTOS = Arrays.asList(postDetailsDTO);
        return postDetailsDTOS;
    }

    public FullUserDetail getFullUserDetail(int channelId, String userName) {
        String uri = "http://10.177.1.170/user/findByChannelIdAndUsername/" + channelId + "/" + userName;
        ResponseEntity<FullUserDetail> responseEntity = restTemplate.getForEntity(uri, FullUserDetail.class);
        FullUserDetail fullUserDetail = responseEntity.getBody();
        return fullUserDetail;
    }
}