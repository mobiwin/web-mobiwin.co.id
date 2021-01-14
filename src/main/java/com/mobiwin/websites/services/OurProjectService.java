package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.OurProjectRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OurProjectService {

    @Autowired
    OurProjectRepo ourProjectRepo;

    public void serviceInsert(String one, String two) {

        ourProjectRepo.repoInsert(one, two);

    }
}
