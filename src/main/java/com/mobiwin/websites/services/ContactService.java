package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.ContactRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ContactService {

    @Autowired
    ContactRepo contactRepo;

    public void contactSave(String name,String email, String departement,String subject,String pesan) {
        contactRepo.contactSave(name,email,departement,subject,pesan);
    }
}
