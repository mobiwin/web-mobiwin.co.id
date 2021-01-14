package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.AdminRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepo adminRepo;

    public void serviceInsert(String one, String two) {

        adminRepo.repoInsert(one, two);

    }
}
