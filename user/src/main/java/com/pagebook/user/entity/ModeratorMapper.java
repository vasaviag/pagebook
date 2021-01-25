package com.pagebook.user.entity;

import javax.persistence.*;

@Entity
@Table(name = "moderatormapper")
public class ModeratorMapper {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String businessId;
    private String moderatorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(String moderatorId) {
        this.moderatorId = moderatorId;
    }
}
