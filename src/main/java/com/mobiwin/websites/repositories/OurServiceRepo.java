package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.OurServiceModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OurServiceRepo extends JpaRepository<OurServiceModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO our_service_tb(icon_path,short_wording,full_wording) VALUES(:icon_path, :short_wording, :full_wording)", nativeQuery = true)
    public void serviceSave(
        @Param("icon_path") String icon_path,
        @Param("short_wording") String short_wording,
        @Param("full_wording") String full_wording
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE our_service_tb SET icon_path = :icon_path, short_wording = :short_wording, full_wording = :full_wording WHERE id = :id", nativeQuery = true)
    public void serviceUpdate(
        @Param("id") long id,
        @Param("icon_path") String icon_path,
        @Param("short_wording") String short_wording,
        @Param("full_wording") String full_wording
    );
}
