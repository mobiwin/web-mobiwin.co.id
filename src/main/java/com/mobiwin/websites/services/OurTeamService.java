package com.mobiwin.websites.services;

import java.util.List;

import com.mobiwin.websites.models.OurTeamModel;
import com.mobiwin.websites.repositories.OurTeamRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OurTeamService {

    @Autowired
    OurTeamRepo ourTeamRepo;

    public List<OurTeamModel> listAllTeam() {
        return ourTeamRepo.findAll();
    }

    public OurTeamModel listTeamById(Long id) {
        return ourTeamRepo.findById(id).get();
    }

    public void saveTeam(OurTeamModel ourTeamModel) {
        ourTeamRepo.save(ourTeamModel);
    }

    public void updatePartDataTeam(String valEmpName, String valPos, String valBio, String valId) {
        ourTeamRepo.repoUpdate(valEmpName, valPos, valBio, valId);
    }

    public void deleteTeam(Long id) {
        ourTeamRepo.deleteById(id);
    }
}
