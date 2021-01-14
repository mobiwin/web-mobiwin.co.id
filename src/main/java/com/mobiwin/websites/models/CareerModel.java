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
    private Date potitionDesc;

    @Column(name = "created_at")
    private Date createdAt;
}
