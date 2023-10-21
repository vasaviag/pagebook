package com.pagebook.user.services;

import com.pagebook.user.dto.RequestDetails;
import com.pagebook.user.entity.FriendRequest;
import com.pagebook.user.entity.User;

import java.util.List;

public interface IFriendRequestService {
    FriendRequest save(FriendRequest friendRequest);

    FriendRequest updateRequest(FriendRequest friendRequest);

    List<RequestDetails> allFriendRequests(String userId);

    List<FriendRequest> findAll();
}
