package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.OurProjectModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OurProjectRepo extends JpaRepository<OurProjectModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE our_project_tb SET preview_path = :nameImg, project_title = :title, client = :client, technology = :technology,kind = :kind  WHERE id = :id", nativeQuery = true)
    public void projectUpdate(
        @Param("id") long id,
        @Param("title") String title,
        @Param("kind") String kind,
        @Param("client") String client,
        @Param("technology") String technology,
        @Param("nameImg") String nameImg
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE our_project_tb SET  project_title = :title, client = :client, technology = :technology,kind = :kind  WHERE id = :id", nativeQuery = true)
    public void projectUpdateWithOutImg(
        @Param("id") long id,
        @Param("title") String title,
        @Param("kind") String kind,
        @Param("client") String client,
        @Param("technology") String technology
    );
}
