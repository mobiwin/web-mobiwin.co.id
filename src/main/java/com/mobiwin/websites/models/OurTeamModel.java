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

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "website")
    private String website;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "facebook")
    private String facebook;
    
    @Column(name = "created_at")
    private Date createdAt;



    public OurTeamModel() {
    }

    public OurTeamModel(Long id, String avatarPath, String employeeName, String potition, String bio, String email, String address, String website, String twitter, String instagram, String facebook, Date createdAt) {
        this.id = id;
        this.avatarPath = avatarPath;
        this.employeeName = employeeName;
        this.potition = potition;
        this.bio = bio;
        this.email = email;
        this.address = address;
        this.website = website;
        this.twitter = twitter;
        this.instagram = instagram;
        this.facebook = facebook;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTwitter() {
        return this.twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return this.instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return this.facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public OurTeamModel id(Long id) {
        setId(id);
        return this;
    }

    public OurTeamModel avatarPath(String avatarPath) {
        setAvatarPath(avatarPath);
        return this;
    }

    public OurTeamModel employeeName(String employeeName) {
        setEmployeeName(employeeName);
        return this;
    }

    public OurTeamModel potition(String potition) {
        setPotition(potition);
        return this;
    }

    public OurTeamModel bio(String bio) {
        setBio(bio);
        return this;
    }

    public OurTeamModel email(String email) {
        setEmail(email);
        return this;
    }

    public OurTeamModel address(String address) {
        setAddress(address);
        return this;
    }

    public OurTeamModel website(String website) {
        setWebsite(website);
        return this;
    }

    public OurTeamModel twitter(String twitter) {
        setTwitter(twitter);
        return this;
    }

    public OurTeamModel instagram(String instagram) {
        setInstagram(instagram);
        return this;
    }

    public OurTeamModel facebook(String facebook) {
        setFacebook(facebook);
        return this;
    }

    public OurTeamModel createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", avatarPath='" + getAvatarPath() + "'" +
            ", employeeName='" + getEmployeeName() + "'" +
            ", potition='" + getPotition() + "'" +
            ", bio='" + getBio() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", website='" + getWebsite() + "'" +
            ", twitter='" + getTwitter() + "'" +
            ", instagram='" + getInstagram() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
    
}
