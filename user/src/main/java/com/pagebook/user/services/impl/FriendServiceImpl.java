package com.pagebook.user.services.impl;

import com.pagebook.user.dto.FriendDetails;
import com.pagebook.user.dto.FriendRequestDetails;
import com.pagebook.user.entity.Friend;
import com.pagebook.user.entity.User;
import com.pagebook.user.repository.IFriendRepository;
import com.pagebook.user.repository.IUserRepository;
import com.pagebook.user.services.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FriendServiceImpl implements IFriendService {

    @Autowired
    IFriendRepository iFriendRepository;

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public Friend save(Friend friend) {

        return iFriendRepository.save(friend);
    }

    @Override
    public List<Friend> findAll() {
        Iterable<Friend> friendIterable = iFriendRepository.findAll();
        List<Friend> friendList = new ArrayList<>();
        friendIterable.forEach(friendList::add);
        return friendList;

    }

    @Override
    public List<FriendDetails> allFollowings(String userId) {

        Iterable<Friend> friendIterable = iFriendRepository.findByUserId(userId);
        List<Friend> friendList = new ArrayList<>();
        friendIterable.forEach(friendList::add);

        List<FriendDetails> friendDetailsList = new ArrayList<>();
        for (Friend friend : friendList) {
            FriendDetails friendDetails = new FriendDetails();
            User user = iUserRepository.findByUserId(friend.getFriendUserId());
            friendDetails.setProfileImage(user.getProfileImage());
            friendDetails.setUserId(user.getUserId());
            friendDetails.setUserName(user.getUserName());
            friendDetailsList.add(friendDetails);
        }

        return friendDetailsList;

    }

    @Override
    public boolean deleteFriend(String userId, String friendUserId) {
        Friend t = new Friend();
        t.setUserId(userId);
        t.setFriendUserId(friendUserId);
        try{
            iFriendRepository.delete(t);
            return true;

        }
        catch(Exception e){
            return false;
        }

    }

    @Override
    public List<FriendDetails> youMayKnow(String userId) {
        Iterable<String> mayKnowFriendsIterable = iFriendRepository.youMayKnow(userId);
        List<String> mayKnowFriendsId = new ArrayList<>();
        mayKnowFriendsIterable.forEach(mayKnowFriendsId::add);
        System.out.println(mayKnowFriendsId.size());

        List<FriendDetails> mayKnowFriendsList = new ArrayList<>();
        for(int i=0;i<mayKnowFriendsId.size();i++){
            User user = iUserRepository.findByUserId(mayKnowFriendsId.get(i));
            FriendDetails friendDetails = new FriendDetails();
            friendDetails.setProfileImage(user.getProfileImage());
            friendDetails.setUserId(user.getUserId());
            friendDetails.setUserName(user.getUserName());
            mayKnowFriendsList.add(friendDetails);
        }
        return mayKnowFriendsList;
    }
}
