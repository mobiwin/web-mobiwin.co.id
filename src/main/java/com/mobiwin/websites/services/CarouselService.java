package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.CarouselRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarouselService {

    @Autowired
    CarouselRepo carouselRepo;

    public void serviceInsert(String one, String two) {

        carouselRepo.repoInsert(one, two);

    }
}
