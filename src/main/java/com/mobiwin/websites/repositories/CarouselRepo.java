package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.CarouselModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarouselRepo extends JpaRepository<CarouselModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO carousel_tb (orders,carousel_image,caption) VALUES(:order,:nameImg, :caption)", nativeQuery = true)
    public void sliderSave(
        @Param("order") Integer order,
        @Param("nameImg") String nameImg,
        @Param("caption") String caption
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE carousel_tb SET orders=:orders,carousel_image= :nameImg, caption= :caption WHERE id = :id", nativeQuery = true)
    public void sliderUpdate(
        @Param("id") long id,
        @Param("orders") long orders,
        @Param("nameImg") String nameImg,
        @Param("caption") String caption
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE carousel_tb SET orders=:orders, caption= :caption WHERE id = :id", nativeQuery = true)
    public void sliderUpdateWithOutImg(
        @Param("id") long id,
        @Param("orders") long orders,
        @Param("caption") String caption
    );
}
