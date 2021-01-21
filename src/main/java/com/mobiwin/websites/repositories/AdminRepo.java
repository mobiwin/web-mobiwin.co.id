package com.mobiwin.websites.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.mobiwin.websites.models.AdminModel;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminRepo extends CrudRepository<AdminModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO aaaa(xxxx, yyyy) VALUES(:satu, :satu)", nativeQuery = true)
    public void repoInsert(
        @Param("satu") String txtSatu,
        @Param("satu") String txtDua
    );

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM admin_tb WHERE user_name = :username_value AND user_password = :password_value LIMIT 1", nativeQuery = true)
    List<AdminModel> repoFindByPassword(@Param("password_value") String passwordUsr,@Param("username_value") String userName);

    @Modifying
    @Query(value = "SELECT * FROM admin_tb WHERE id <> :id_value", nativeQuery = true)
    List<AdminModel> repoFindUsersExcept(@Param("id_value") String idSessi);
}
