package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.OurClientModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OurClientRepo extends JpaRepository<OurClientModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE our_client_tb SET client_name = :clnName, year = :year WHERE id = :id", nativeQuery = true)
    public void repoUpdatePart(
        @Param("clnName") String txtClnName,
        @Param("year") String txtYear,
        @Param("id") String txtId
    ); 
}
