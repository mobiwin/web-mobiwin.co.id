package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.AboutUsModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AboutUsRepo extends JpaRepository<AboutUsModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO aaaa(xxxx, yyyy) VALUES(:satu, :satu)", nativeQuery = true)
    public void repoInsert(
        @Param("satu") String txtSatu,
        @Param("satu") String txtDua
    );

    @Transactional
    @Modifying
    @Query(value = "update about_us_tb set title = :title, summary = :summary, wording = :wording where id=:id",nativeQuery = true)
    public void updateSql(
    @Param("id") long id,
    @Param("title") String title,
    @Param("summary") String summary,
    @Param("wording") String wording);
}
