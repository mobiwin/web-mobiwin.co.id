package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.OurClientRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OurClientService {

    @Autowired
    OurClientRepo ourClientRepo;

    public void serviceInsert(String one, String two) {

        ourClientRepo.repoInsert(one, two);

    }
}
