package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.TestimonyModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TestimonyRepo extends JpaRepository<TestimonyModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE testimony_tb SET user_ava_path = :nameImg, name_user = :name_user, company = :company, testimony_text = :testimony_text  WHERE id = :id", nativeQuery = true)
    public void testimonyUpdate(
        @Param("id") long id,
        @Param("name_user") String name_user,
        @Param("company") String company,
        @Param("testimony_text") String testimony_text,
        @Param("nameImg") String nameImg
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE testimony_tb SET name_user = :name_user, company = :company, testimony_text = :testimony_text  WHERE id = :id", nativeQuery = true)
    public void testimonyUpdateWithOutImg(
        @Param("id") long id,
        @Param("name_user") String name_user,
        @Param("company") String company,
        @Param("testimony_text") String testimony_text
    );
}
