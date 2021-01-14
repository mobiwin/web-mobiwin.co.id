package com.mobiwin.websites.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "our_project_tb")
public class OurProjectModel {
    
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "preview_path")
    private String previewPath;

    @Column(name = "peoject_title")
    private String peojectTitle;

    @Column(name = "year")
    private String year;

    @Column(name = "name_user")
    private String nameUser;

    @Column(name = "tecknology")
    private String tecknology;
    
    @Column(name = "created_at")
    private Date createdAt;
}
