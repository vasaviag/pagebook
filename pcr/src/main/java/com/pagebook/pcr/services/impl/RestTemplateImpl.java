package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.UserDTO;
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

    public UserDTO getUserDetails(String userId)
    {
        String uri = "http://10.177.1.179:7081/pb/user/getUserInfo/" + userId;
        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(uri, UserDTO.class);
        UserDTO userDTO = responseEntity.getBody();
        return userDTO;
    }

    public List<UserDTO> getFriendsDetails(String userId)
    {
        String uri = "http://10.177.1.179:7081/pb/user/getFriends/" + userId;
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
}
