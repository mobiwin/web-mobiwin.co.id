package com.mobiwin.websites.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "about_us_tb")
public class AboutUsModel {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "wording")
    private String wording;
    
    @Column(name = "created_at")
    private Date createdAt;


    public AboutUsModel() {
    }

    public AboutUsModel(Long id, String title, String summary, String wording, Date createdAt) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.wording = wording;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWording() {
        return this.wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
