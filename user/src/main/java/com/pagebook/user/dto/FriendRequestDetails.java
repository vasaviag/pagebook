package com.pagebook.user.dto;

public class FriendRequestDetails {
    private String senderId;
    private String receiverId;
    private int status; //{1,2,3} [1->accepted, 2->pending, 3->rejected]

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
