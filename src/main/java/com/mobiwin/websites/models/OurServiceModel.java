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
    
    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "short_wording")
    private String shortWording;

    @Column(name = "full_wording")
    private String fullWording;
    
    @Column(name = "created_at")
    private Date createdAt;
}
