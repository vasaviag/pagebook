package com.pagebook.user.repository;


import com.pagebook.user.entity.FriendRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IFriendRequestRepository extends CrudRepository<FriendRequest,Integer> {
    FriendRequest findByRequestId(int requestId);

    List<FriendRequest> findByReceiverId(String userId);

}
