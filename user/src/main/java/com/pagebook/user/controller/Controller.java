package com.pagebook.user.controller;


import com.pagebook.user.dto.*;
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
import org.springframework.transaction.annotation.Transactional;
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


    @GetMapping(value = "/getFollowings/{userId}")
    List<FriendDetails>allFollowings(@PathVariable("userId") String userId){
        return iFriendService.allFollowings(userId);
    }
    @GetMapping(value = "/getFollowers/{userId}")
    List<FriendDetails>allFollowers(@PathVariable("userId") String userId){
        return iFriendService.allFollowers(userId);
    }

    @GetMapping(value= "isFriend/{userId}/{friendUserId}")
    public boolean isFriend(@PathVariable("userId")String userId,@PathVariable("friendUserId")String friendUserId){
        return iFriendService.isFriend(userId, friendUserId);
    }

    @GetMapping(value= "isModeratorFor/{moderatorId}/{businessId}")
    public boolean isModeratorFor(@PathVariable("moderatorId")String moderatorId,@PathVariable("businessId")String businessId){
        return iModeratorMapperService.isModeratorFor(moderatorId, businessId);
    }

    @GetMapping(value= "isModerator/{moderatorId}")
    public boolean isModerator(@PathVariable("moderatorId")String moderatorId){
        return iModeratorMapperService.isModerator(moderatorId);
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

    @Transactional
    @DeleteMapping(value = "/removeModerator")
    public void deletemoderator(@RequestBody ModeratorMapper moderatorMapper){
        iModeratorMapperService.deleteModerator(moderatorMapper);

    }

    @GetMapping(value = "/pagesManaged/{userId}")
    public List<User> pagesManaged(@PathVariable("userId") String userId){
        return iModeratorMapperService.pagesManaged(userId);
    }

    @GetMapping(value = "/allModerators/{userId}")
    public List<User> allModerators(@PathVariable("userId") String userId){
        return iModeratorMapperService.allModerators(userId);
    }

    @GetMapping(value = "/myProfile/{userId}")
    public MyProfile getMyProfile(@PathVariable("userId") String userId){
        return iUserService.getMyProfile(userId);
    }

    @GetMapping(value = "/userProfile/{userId}/{friendUserId}")
    public FriendProfile getUserProfile(@PathVariable("userId") String userId, @PathVariable("friendUserId") String friendUserId){
        return iUserService.getUserProfile(userId, friendUserId);
    }

}
