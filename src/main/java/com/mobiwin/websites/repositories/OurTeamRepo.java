package com.mobiwin.websites.repositories;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.OurTeamModel;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OurTeamRepo extends CrudRepository<OurTeamModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO aaaa(xxxx, yyyy) VALUES(:satu, :satu)", nativeQuery = true)
    public void repoInsert(
        @Param("satu") String txtSatu,
        @Param("satu") String txtDua
    );
}
