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
    
    @Column(name = "carousel_image",nullable = true)
    private String carouselImage;

    @Column(name = "caption")
    private String caption;
    
    @Column(name = "created_at" ,nullable = true)
    private Date createdAt;
    

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the carouselImage
     */
    public String getCarouselImage() {
        return carouselImage;
    }

    /**
     * @param carouselImage the carouselImage to set
     */
    public void setCarouselImage(String carouselImage) {
        this.carouselImage = carouselImage;
    }

    /**
     * @return String return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return Date return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
