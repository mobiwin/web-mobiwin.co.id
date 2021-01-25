package com.mobiwin.websites.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "our_team_tb")
public class OurTeamModel {
    
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "potition")
    private String potition;

    @Column(name = "bio")
    private String bio;
    
    @Column(name = "created_at")
    private Date createdAt;


    public OurTeamModel() {
    }

    public OurTeamModel(Long id, String avatarPath, String employeeName, String potition, String bio, Date createdAt) {
        this.id = id;
        this.avatarPath = avatarPath;
        this.employeeName = employeeName;
        this.potition = potition;
        this.bio = bio;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarPath() {
        return this.avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPotition() {
        return this.potition;
    }

    public void setPotition(String potition) {
        this.potition = potition;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
