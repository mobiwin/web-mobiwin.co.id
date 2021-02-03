package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.CandidateModel;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CandidateRepo extends CrudRepository<CandidateModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO candidate_tb(id_career,candidate_desc,candidate_name,candidate_email,candidate_skill,candidate_cv_path,status) VALUES(:id_career,:candidate_desc,:candidate_name,:candidate_email,:candidate_skill,:fileName,:status)", nativeQuery = true)
    public void saveData(
        @Param("id_career") long id_career,
        @Param("candidate_desc") String candidate_desc,
        @Param("candidate_name") String candidate_name,
        @Param("candidate_email") String candidate_email,
        @Param("candidate_skill") String candidate_skill,
        @Param("fileName") String fileName,
        @Param("status") String status
    );
}
