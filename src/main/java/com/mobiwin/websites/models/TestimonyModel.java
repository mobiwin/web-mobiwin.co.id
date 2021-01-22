package com.mobiwin.websites.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testimony_tb")
public class TestimonyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_ava_path")
    private String userAvaPath;

    @Column(name = "name_user")
    private String nameUser;

    @Column(name = "company")
    private String company;

    @Column(name = "testimony_text")
    private String testimonyText;

    @Column(name = "created_at")
    private Date createdAt;

    public TestimonyModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAvaPath() {
        return this.userAvaPath;
    }

    public void setUserAvaPath(String userAvaPath) {
        this.userAvaPath = userAvaPath;
    }

    public String getNameUser() {
        return this.nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTestimonyText() {
        return this.testimonyText;
    }

    public void setTestimonyText(String testimonyText) {
        this.testimonyText = testimonyText;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}