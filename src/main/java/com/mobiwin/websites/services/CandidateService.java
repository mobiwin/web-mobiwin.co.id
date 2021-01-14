package com.mobiwin.websites.services;

import com.mobiwin.websites.repositories.CandidateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    @Autowired
    CandidateRepo candidateRepo;

    public void serviceInsert(String one, String two) {

        candidateRepo.repoInsert(one, two);

    }
}
