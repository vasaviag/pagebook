package com.pagebook.pcr.dto;

public class CRMDTO {
    private String leadID;
    private String leadName;
    private int leadType;
    private String postID;
    private String businessID;

    public String getLeadID() {
        return leadID;
    }

    public void setLeadID(String leadID) {
        this.leadID = leadID;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public int getLeadType() {
        return leadType;
    }

    public void setLeadType(int leadType) {
        this.leadType = leadType;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getBusinessID() {
        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }
}
