package com.pagebook.user.controller;


import com.pagebook.user.dto.FriendDetails;
import com.pagebook.user.dto.FriendRequestDetails;
import com.pagebook.user.dto.RequestDetails;
import com.pagebook.user.entity.Friend;
import com.pagebook.user.entity.FriendRequest;
import com.pagebook.user.entity.ModeratorMapper;
import com.pagebook.user.entity.User;
import com.pagebook.user.services.IFriendRequestService;
import com.pagebook.user.services.IFriendService;
import com.pagebook.user.services.IModeratorMapperService;
import com.pagebook.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/pb/user")
public class Controller {

    @Autowired
    IFriendService iFriendService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IFriendRequestService iFriendRequestService;
    @Autowired
    IModeratorMapperService iModeratorMapperService;

    @PostMapping(value = "/addFriend")
    public Friend save(@RequestBody Friend friend){
        return iFriendService.save(friend);
    }

    @PostMapping(value = "/addUserDetails")
    public User save(@RequestBody User user){
        return iUserService.save(user);
    }

    @GetMapping(value = "/findAll")
    List<User> allUsers(){
        return iUserService.findAll();
    }

    @GetMapping(value = "/getUserInfo/{userId}")
    User getUserInfo(@PathVariable("userId") String userId){
        return iUserService.getUserInfo(userId);
    }


    @PostMapping(value = "/userDetailsList")
    public List<User> userDetailsList(@RequestBody List<String> userIdList){
        return iUserService.userDetailsList(userIdList);
    }


    @PostMapping(value = "/addFriendRequest")
    public FriendRequest addFriendRequest(@RequestBody FriendRequest friendRequest){
        return iFriendRequestService.save(friendRequest);
    }

    @PostMapping(value = "/updateFriendRequest")
    public FriendRequest updateFriendRequest(@RequestBody FriendRequest friendRequest){
        return iFriendRequestService.updateRequest(friendRequest);
    }

    @GetMapping(value = "/getFriendRequests/{userId}")
    List<RequestDetails> allPendingFriendRequestsByUserId(@PathVariable("userId") String userId){
        return iFriendRequestService.allFriendRequests(userId);
    }

    @GetMapping(value = "/requests/findAll")
    List<FriendRequest> allFriendRequests(){
        return iFriendRequestService.findAll();
    }


    @GetMapping(value = "/getFriends/{userId}")
    List<FriendDetails>allFollowings(@PathVariable("userId") String userId){
        return iFriendService.allFollowings(userId);
    }

    @GetMapping(value= "isFriend/{userId}/{friendUserId}")
    public boolean isFriend(@PathVariable("userId")String userId,@PathVariable("friendUserId")String friendUserId){
        return iFriendService.isFriend(userId, friendUserId);
    }

    @GetMapping(value = "/youMayKnow/{userId}")
    List<FriendDetails>youMayKnow(@PathVariable("userId") String userId){
        return iFriendService.youMayKnow(userId);
    }

    @GetMapping(value="friends/findAll")
    List<Friend> findAllFriends(){

        return iFriendService.findAll();
    }

    @DeleteMapping(value = "/deleteFriend/{userId}/{friendUserId}")
    public boolean deleteFriend(@PathVariable("userId") String userId, @PathVariable("friendUserId") String friendUserId){
        return iFriendService.deleteFriend(userId, friendUserId);
    }

    @PostMapping(value = "/addModerator")
    public ModeratorMapper addmoderator(@RequestBody ModeratorMapper moderatorMapper){
        return iModeratorMapperService.save(moderatorMapper);

    }

    @GetMapping(value = "/pagesManaged/{userId}")
    public List<User> pagesManaged(@PathVariable("userId") String userId){
        return iModeratorMapperService.pagesManaged(userId);
    }

    @GetMapping(value = "/allModerators/{userId}")
    public List<User> allModerators(@PathVariable("userId") String userId){
        return iModeratorMapperService.allModerators(userId);
    }


}
