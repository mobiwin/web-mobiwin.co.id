package com.mobiwin.websites.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "our_service_tb")
public class OurServiceModel {
    
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title")
    private String title;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "short_wording")
    private String shortWording;

    @Column(name = "full_wording")
    private String fullWording;
    
    @Column(name = "created_at")
    private Date CreatedAt;



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

    public String getIconPath() {
        return this.iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getShortWording() {
        return this.shortWording;
    }

    public void setShortWording(String shortWording) {
        this.shortWording = shortWording;
    }

    public String getFullWording() {
        return this.fullWording;
    }

    public void setFullWording(String fullWording) {
        this.fullWording = fullWording;
    }

    public Date getCreatedAt() {
        return this.CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }
   

}
