package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.OurTeamRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OurTeamService {

    @Autowired
    OurTeamRepo ourTeamRepo;

    public void serviceInsert(String one, String two) {

        ourTeamRepo.repoInsert(one, two);

    }
}
