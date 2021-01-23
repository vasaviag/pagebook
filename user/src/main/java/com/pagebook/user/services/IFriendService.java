package com.pagebook.user.services;

import com.pagebook.user.dto.FriendDetails;
import com.pagebook.user.entity.Friend;

import java.util.List;

public interface IFriendService {
    List<Friend> findAll();

    Friend save(Friend friend);

    List<FriendDetails> allFollowings(String userId);

    List<FriendDetails> youMayKnow(String userId);

    boolean deleteFriend(String userId, String friendUserId);
}
