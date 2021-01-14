package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.AboutUsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutUsService {

    @Autowired
    AboutUsRepo aboutUsRepo;

    public void serviceInsert(String one, String two) {

        aboutUsRepo.repoInsert(one, two);

    }
}
