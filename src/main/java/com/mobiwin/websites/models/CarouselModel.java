package com.mobiwin.websites.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carousel_tb")
public class CarouselModel {
    
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "carousel_image")
    private String carouselImage;

    @Column(name = "caption")
    private String caption;
    
    @Column(name = "created_at")
    private Date createdAt;
}
