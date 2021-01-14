package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.TestimonyRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonyService {

    @Autowired
    TestimonyRepo testimonyRepo;

    public void serviceInsert(String one, String two) {

        testimonyRepo.repoInsert(one, two);

    }
}
