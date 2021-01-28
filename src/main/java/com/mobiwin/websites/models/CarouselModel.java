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

    @Column(name="orders")
    private Long order;

    @Column(name = "carousel_image",nullable = true)
    private String carouselImage;

    @Column(name = "caption")
    private String caption;
    
    @Column(name = "created_at" ,nullable = true)
    private Date createdAt;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrders() {
        return this.order;
    }

    public void setOrders(Long order) {
        this.order = order;
    }

    public String getCarouselImage() {
        return this.carouselImage;
    }

    public void setCarouselImage(String carouselImage) {
        this.carouselImage = carouselImage;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
   
    

}
