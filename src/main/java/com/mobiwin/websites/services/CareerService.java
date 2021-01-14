package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.CareerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerService {

    @Autowired
    CareerRepo careerRepo;

    public void serviceInsert(String one, String two) {

        careerRepo.repoInsert(one, two);

    }
}
