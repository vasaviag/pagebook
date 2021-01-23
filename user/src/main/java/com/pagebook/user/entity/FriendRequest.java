package com.pagebook.user.entity;

import javax.persistence.*;
@Entity
@Table(name = "requests")
public class FriendRequest {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int requestId;

    private String senderId;
    private String receiverId;
    private int status; //{1,2,3} [1->accepted, 2->pending, 3->rejected]

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
