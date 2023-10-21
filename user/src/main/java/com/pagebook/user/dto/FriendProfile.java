package com.pagebook.user.dto;

import com.pagebook.user.entity.User;

import java.util.Date;
import java.util.List;

public class FriendProfile {
    private String profileImage;
    private String username;
    private String dateOfBirth;
    private String bio;
    private int followerCount;
    private int followingCount;
    private boolean isFriend;
    private boolean isModerator;
    private int type;
    private List<PostDetailsDTO> postDetailsDTOS;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public boolean getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(boolean isFriend) {
        this.isFriend = isFriend;
    }

    public boolean getIsModerator() {
        return isModerator;
    }

    public void setIsModerator(boolean isModerator) {
        this.isModerator = isModerator;
    }

    public List<PostDetailsDTO> getPostDetailsDTOS() {
        return postDetailsDTOS;
    }

    public void setPostDetailsDTOS(List<PostDetailsDTO> postDetailsDTOS) {
        this.postDetailsDTOS = postDetailsDTOS;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public boolean isModerator() {
        return isModerator;
    }

    public void setModerator(boolean moderator) {
        isModerator = moderator;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
