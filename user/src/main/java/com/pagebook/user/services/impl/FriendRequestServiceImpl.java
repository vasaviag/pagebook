package com.pagebook.user.services.impl;

import com.pagebook.user.dto.RequestDetails;
import com.pagebook.user.entity.Friend;
import com.pagebook.user.entity.FriendRequest;
import com.pagebook.user.entity.User;
import com.pagebook.user.repository.IFriendRepository;
import com.pagebook.user.repository.IFriendRequestRepository;
import com.pagebook.user.repository.IUserRepository;
import com.pagebook.user.services.IFriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendRequestServiceImpl implements IFriendRequestService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IFriendRepository iFriendRepository;

    @Autowired
    IFriendRequestRepository iFriendRequestRepository;

    @Override
    public List<FriendRequest> findAll() {
        Iterable<FriendRequest> friendRequestsIterable = iFriendRequestRepository.findAll();
        List<FriendRequest> friendRequestList = new ArrayList<>();
        friendRequestsIterable.forEach(friendRequestList::add);
        return friendRequestList;
    }

    @Override
    public FriendRequest save(FriendRequest friendRequest) {

//        User sender = iUserRepository.findByUserId(friendRequest.getSenderId());
//        User receiver = iUserRepository.findByUserId(friendRequest.getReceiverId());

        iFriendRequestRepository.save(friendRequest);
        if(friendRequest.getStatus()==1){
            Friend friend = new Friend();
            friend.setUserId(friendRequest.getSenderId());
            friend.setFriendUserId(friendRequest.getReceiverId());
            iFriendRepository.save(friend);
        }
        return friendRequest;

    }

    @Override
    public FriendRequest updateRequest(FriendRequest friendRequest) {
        FriendRequest friendRequestTemp = iFriendRequestRepository.findByRequestId(friendRequest.getRequestId());
        friendRequestTemp.setStatus(friendRequest.getStatus());
        iFriendRequestRepository.save(friendRequestTemp);

//        User sender = iUserRepository.findByUserId(friendRequest.getSenderId());
//        User receiver = iUserRepository.findByUserId(friendRequest.getReceiverId());

        if(friendRequest.getStatus()==1){
            Friend friend = new Friend();
            friend.setUserId(friendRequest.getSenderId());
            friend.setFriendUserId(friendRequest.getReceiverId());
            iFriendRepository.save(friend);
        }
        return friendRequest;
    }


    @Override
    public List<RequestDetails> allFriendRequests(String userId) {

        Iterable<FriendRequest> friendRequestsIterable =  iFriendRequestRepository.findByReceiverId(userId);
        List<FriendRequest> friendRequestList= new ArrayList<>();
        friendRequestsIterable.forEach(friendRequestList::add);

        List<RequestDetails> requestDetailsList = new ArrayList<>();
        for(FriendRequest friendRequest:friendRequestList) {
            RequestDetails requestDetails = new RequestDetails();
            if(friendRequest.getStatus()==2){
                User user = iUserRepository.findByUserId(friendRequest.getSenderId());
                requestDetails.setRequestId(friendRequest.getRequestId());
                requestDetails.setProfileImage(user.getProfileImage());
                requestDetails.setStatus(friendRequest.getStatus());
                requestDetails.setUserId(user.getUserId());
                requestDetails.setUserName(user.getUserName());
                requestDetailsList.add(requestDetails);

            }

        }
        return requestDetailsList;


    }
}
