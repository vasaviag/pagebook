package com.pagebook.user.services.impl;

import com.pagebook.user.dto.AnalyticsVFDTO;
import com.pagebook.user.dto.CRMDTO;
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
        String uri = "http://10.177.1.69:8081/pb/post/getUserPosts/" + userId;
        ResponseEntity<PostDetailsDTO[]> responseEntity = restTemplate.getForEntity(uri, PostDetailsDTO[].class);
        PostDetailsDTO postDetailsDTO[] = responseEntity.getBody();
        List<PostDetailsDTO> postDetailsDTOS = Arrays.asList(postDetailsDTO);
        return postDetailsDTOS;
    }

    public FullUserDetail getFullUserDetail(int channelId, String userName) {
        String uri = "http://10.177.1.104:8085/user/findByChannelIdAndUsername/" + channelId + "/" + userName;
        ResponseEntity<FullUserDetail> responseEntity = restTemplate.getForEntity(uri, FullUserDetail.class);
        FullUserDetail fullUserDetail = responseEntity.getBody();
        return fullUserDetail;
    }

    public void sendToAnalytics(AnalyticsVFDTO analyticsVFDTO)
    {
        String uri = "http://10.177.1.164:8080/analytics";
        restTemplate.postForEntity(uri, analyticsVFDTO, null);
    }

    public void sendFollowToCRM(CRMDTO crmdto)
    {
        String uri = "http://10.177.2.81:8001/lead/0";
        restTemplate.postForEntity(uri, crmdto, null);
    }

}