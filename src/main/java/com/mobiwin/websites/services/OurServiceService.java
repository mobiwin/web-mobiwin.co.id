package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.OurServiceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OurServiceService {

    @Autowired
    OurServiceRepo ourServiceRepo;

    public void serviceInsert(String one, String two) {

        ourServiceRepo.repoInsert(one, two);

    }
}
