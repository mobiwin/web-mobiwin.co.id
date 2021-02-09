package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.CareerModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CareerRepo extends JpaRepository<CareerModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE career_tb SET job_title = :jobTitle, potition = :pos, requirement = :req, potition_desc = :posdesc WHERE id = :id", nativeQuery = true)
    public void repoUpdate(
        @Param("jobTitle") String txtJobTitle,
        @Param("pos") String txtPos,
        @Param("req") String txtReq,
        @Param("posdesc") String txtPosDesc,
        @Param("id") String txtId
    );

}
