package com.mobiwin.websites.services;

import java.util.List;

import com.mobiwin.websites.models.CandidateModel;
import com.mobiwin.websites.repositories.CandidateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    @Autowired
    CandidateRepo candidateRepo;

    public void saveData(long id_career,String candidate_desc,String candidate_name,String candidate_email,String candidate_skill,String fileName,String status) {
        candidateRepo.saveData(id_career,candidate_desc,candidate_name,candidate_email,candidate_skill,fileName,status);
    }
    public CandidateModel findOne(long id) {
        return candidateRepo.findById(id).get();
    }
    public List<CandidateModel> hasNotBeenSeen(long id,String status){
        var repoDataNotBeenSeen = (List<CandidateModel>) candidateRepo.hasNotBeenSeen(id,status);
        return repoDataNotBeenSeen;
    }

    public List<CandidateModel> hasBeenSeen(long id,String status){
        var repoDataBeenSeen = (List<CandidateModel>) candidateRepo.hasBeenSeen(id,status);
        return repoDataBeenSeen;
    }

    public void candidateUpdate(long id,String status) {
        candidateRepo.candidateUpdate(id,status);
    }
}
