package com.pagebook.user.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pagebook.user.dto.*;
import com.pagebook.user.entity.User;
import com.pagebook.user.repository.IModeratorMapperRepository;
import com.pagebook.user.repository.IUserRepository;
import com.pagebook.user.services.IFriendService;
import com.pagebook.user.services.IModeratorMapperService;
import com.pagebook.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    RestTemplateImpl restTemplateImpl;
    @Autowired
    IFriendService iFriendService;

    @Autowired
    IModeratorMapperService iModeratorMapperService;

    @Override
    public User save(User user)  {
        ObjectMapper objectMapper = new ObjectMapper();

        String userString="";
        try {
            userString = objectMapper.writeValueAsString(user);
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }

        System.out.println("before kafka");
        kafkaTemplate.send("newuser",userString);
        System.out.println("after kafka");
        return iUserRepository.save(user);
    }

    @Override
    public boolean isExisting(String userId) {
        return iUserRepository.existsById(userId);
    }

    @Override
    public List<User> findAll() {
        Iterable<User> userIterable = iUserRepository.findAll();
        List<User> userList = new ArrayList<>();
        userIterable.forEach(userList::add);
        return userList;
    }

    @Override
    public User getUserInfo(String userId) {
        return iUserRepository.findById(userId).get();
    }

    @Override
    public List<User> userDetailsList(List<String> userIdList) {

        Iterable<User> userIterable = iUserRepository.findByUserIdIn(userIdList);
        List<User> userDetailsList = new ArrayList<>();
        userIterable.forEach(userDetailsList::add);
        return userDetailsList;
    }

    public MyProfile getMyProfile(String userId)
    {
        User user = getUserInfo(userId);
        FullUserDetail fullUserDetail = restTemplateImpl.getFullUserDetail( 0, user.getUserName());
        List<PostDetailsDTO> postDetailsDTOS = restTemplateImpl.getUserPosts(userId);
        MyProfile myProfile = new MyProfile();
        myProfile.setBio(fullUserDetail.getBio());
        myProfile.setDateOfBirth(fullUserDetail.getDateOfBirth());
        myProfile.setFollowerCount(iFriendService.allFollowers(userId).size());
        myProfile.setFollowingCount(iFriendService.allFollowings(userId).size());
        myProfile.setType(fullUserDetail.getType());
        myProfile.setPostDetailsDTOS(postDetailsDTOS);
        myProfile.setUsername(fullUserDetail.getUsername());
        myProfile.setProfileImage(fullUserDetail.getProfileImage());
        return myProfile;
    }

    public FriendProfile getUserProfile(String userId, String friendUserId)
    {
        FriendProfile friendProfile = new FriendProfile();
        User user = getUserInfo(friendUserId);
        List<PostDetailsDTO> postDetailsDTOS;
        FullUserDetail fullUserDetail = restTemplateImpl.getFullUserDetail(0, user.getUserName());
        friendProfile.setType(fullUserDetail.getType());
        if(iModeratorMapperService.isModeratorFor(userId, friendUserId))
        {
            friendProfile.setIsFriend(true);
        }
        else
        {
            friendProfile.setIsFriend(false);
        }

        if(iFriendService.isFriend(userId, friendUserId))
        {
            friendProfile.setIsFriend(true);
        }
        else
        {
            friendProfile.setIsFriend(false);
        }

        if((friendProfile.getIsFriend() == false && friendProfile.getType() == 1)
                || (friendProfile.getIsFriend() == true) || (friendProfile.getType()==3))
        {
            postDetailsDTOS = restTemplateImpl.getUserPosts(friendUserId);
        }
        else
        {
            postDetailsDTOS = null;
        }

        friendProfile.setBio(fullUserDetail.getBio());
        friendProfile.setDateOfBirth(fullUserDetail.getDateOfBirth());
        friendProfile.setFollowerCount(iFriendService.allFollowers(friendUserId).size());
        friendProfile.setFollowingCount(iFriendService.allFollowings(friendUserId).size());
        friendProfile.setType(fullUserDetail.getType());
        friendProfile.setPostDetailsDTOS(postDetailsDTOS);
        friendProfile.setUsername(fullUserDetail.getUsername());
        friendProfile.setProfileImage(fullUserDetail.getProfileImage());
        AnalyticsVFDTO analyticsVFDTO = new AnalyticsVFDTO();
        analyticsVFDTO.setUserId(friendUserId);
        analyticsVFDTO.setChannelId(0);
        analyticsVFDTO.setAction("view");
        restTemplateImpl.sendToAnalytics(analyticsVFDTO);
        return friendProfile;
    }
}
