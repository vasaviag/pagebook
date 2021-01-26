package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.*;
import com.pagebook.pcr.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestTemplateImpl {

    @Value("${myapp.pb.userinfo.server}")
    private String PB_USER_INFO_SERVER;

    //todo : use : https://www.baeldung.com/spring-webclient-resttemplate for asynch if required

    @Autowired
    RestTemplate restTemplate;

    //todo : try out cachable on this method
    public UserDTO getUserDetails(String userId)
    {
        System.out.println("for testing " + userId);
        //todo: move the hardcoded i/ps to property file (PB_USER_INFO_SERVER)
        String uri = "http://10.177.1.179:7081/pb/user/getUserInfo/" + userId;
        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(uri, UserDTO.class);
        UserDTO userDTO = responseEntity.getBody();
        return userDTO;
    }

    public List<UserDTO> getFriendsDetails(String userId)
    {

        String uri = "http://10.177.1.179:7081/pb/user/getFollowings/" + userId;
        System.out.println(uri);
        ResponseEntity<UserDTO[]> responseEntity = restTemplate.getForEntity(uri, UserDTO[].class);
        UserDTO users1 [] = responseEntity.getBody();
        List<UserDTO> userDTOS = Arrays.asList(users1);
        return userDTOS;
    }

    public List<UserDTO> getUserDetailsList(List<String> userIds) {
        final String uri = "http://10.177.1.179:7081/pb/user/userDetailsList";
        ResponseEntity<UserDTO[]> responseEntity = restTemplate.postForEntity(uri, userIds, UserDTO[].class);
        UserDTO users1[] = responseEntity.getBody();
        List<UserDTO> userDTOS = Arrays.asList(users1);
        return userDTOS;
    }

    public void sendPostDetailsToCommonInfra(Post post)
    {
        String uri = "http://10.177.2.54:8080/notification/pb/post";
        restTemplate.postForEntity(uri, post, null);
    }

    public void sendReactionDetailsToCommonInfra(ReactionNotificationDTO reactionNotificationDTO)
    {
        String uri = "http://10.177.2.54:8080/notification/pb/reaction";
        restTemplate.postForEntity(uri, reactionNotificationDTO, null);
    }

    public void sendCommentDetailsToCommonInfra(CommentNotificationDTO commentNotificationDTO)
    {
        String uri = "http://10.177.2.54:8080/notification/pb/comment";
        restTemplate.postForEntity(uri, commentNotificationDTO, null);
    }

    public void sendToAnalytics(AnalyticsDTO analyticsDTO)
    {
        String uri = "http://10.177.1.164:8080/analytics";
        restTemplate.postForEntity(uri, analyticsDTO, null);
    }
}
