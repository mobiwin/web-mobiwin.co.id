package com.mobiwin.websites.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mobiwin.websites.models.ContactModel;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepo extends JpaRepository <ContactModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO contact_tb(name,email,departement,subject,pesan) VALUES(:name,:email,:departement,:subject,:pesan)", nativeQuery = true)
    public void contactSave(
        @Param("name") String name,
        @Param("email") String email,
        @Param("departement") String departement,
        @Param("subject") String subject,
        @Param("pesan") String pesan
    );

}
