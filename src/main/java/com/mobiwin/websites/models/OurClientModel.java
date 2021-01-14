package com.mobiwin.websites.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "our_client_tb")
public class OurClientModel {
    
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "preview_path")
    private String previewPath;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "year")
    private String year;
    
    @Column(name = "created_at")
    private Date createdAt;
}
