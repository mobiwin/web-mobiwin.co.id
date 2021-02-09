package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.OurTeamModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OurTeamRepo extends JpaRepository<OurTeamModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE our_team_tb SET employee_name = :empName, potition = :pos, bio = :bio WHERE id = :id", nativeQuery = true)
    public void repoUpdate(
        @Param("empName") String txtEmpName,
        @Param("pos") String txtPos,
        @Param("bio") String txtBio,
        @Param("id") String txtId
    );
}
