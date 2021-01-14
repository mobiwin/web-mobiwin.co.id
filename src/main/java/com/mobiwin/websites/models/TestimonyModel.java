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
	@GeneratedValue (strategy = GenerationType.IDENTITY)
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
}
