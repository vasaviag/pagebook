package com.pagebook.user.entity;

import javax.persistence.*;
@Entity
@Table(name = "friends")
public class Friend {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String userId;
    private String friendUserId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }
}
