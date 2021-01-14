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
}
