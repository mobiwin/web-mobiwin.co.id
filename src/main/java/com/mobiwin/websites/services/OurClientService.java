package com.mobiwin.websites.services;

import java.util.List;

import com.mobiwin.websites.models.OurClientModel;
import com.mobiwin.websites.repositories.OurClientRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OurClientService {

    @Autowired
    OurClientRepo ourClientRepo;

    public List<OurClientModel> listAllClient() {
        return ourClientRepo.findAll();
    }

    public OurClientModel listClientById(Long id) {
        return ourClientRepo.findById(id).get();
    }

    public void saveClient(OurClientModel ourClientModel) {
        ourClientRepo.save(ourClientModel);
    }

    public void updatePartDataClient(String valClnName, String valYear, String valId) {
        ourClientRepo.repoUpdatePart(valClnName, valYear, valId);
    }

    public void deleteClient(Long id) {
        ourClientRepo.deleteById(id);
    }
}
