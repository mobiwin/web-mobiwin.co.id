package com.mobiwin.websites.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "career_tb")
public class CareerModel {
    
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "icon_of")
    private String iconOf;

    @Column(name = "potition")
    private String potition;

    @Column(name = "requirement")
    private String requirement;
    
    @Column(name = "potition_desc")
    private String potitionDesc;

    @Column(name = "created_at")
    private Date createdAt;


    public CareerModel() {
    }

    public CareerModel(Long id, String jobTitle, String iconOf, String potition, String requirement, String potitionDesc, Date createdAt) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.iconOf = iconOf;
        this.potition = potition;
        this.requirement = requirement;
        this.potitionDesc = potitionDesc;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getIconOf() {
        return this.iconOf;
    }

    public void setIconOf(String iconOf) {
        this.iconOf = iconOf;
    }

    public String getPotition() {
        return this.potition;
    }

    public void setPotition(String potition) {
        this.potition = potition;
    }

    public String getRequirement() {
        return this.requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getPotitionDesc() {
        return this.potitionDesc;
    }

    public void setPotitionDesc(String potitionDesc) {
        this.potitionDesc = potitionDesc;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
