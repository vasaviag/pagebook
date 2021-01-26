package com.pagebook.user.services;

import com.pagebook.user.dto.FriendDetails;
import com.pagebook.user.dto.FriendProfile;
import com.pagebook.user.dto.MyProfile;
import com.pagebook.user.entity.Friend;
import com.pagebook.user.entity.FriendRequest;
import com.pagebook.user.entity.User;

import java.util.List;

public interface IUserService {
    User save(User user);

    List<User> findAll();

    User getUserInfo(String userId);

    List<User> userDetailsList(List<String> userIdList);

    MyProfile getMyProfile(String userId);

    FriendProfile getUserProfile(String userId, String friendUserId);

    boolean isExisting(String userId);
}
