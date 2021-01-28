package com.mobiwin.websites.services;

import java.util.List;

import com.mobiwin.websites.models.OurServiceModel;
import com.mobiwin.websites.repositories.OurServiceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OurServiceService {

    @Autowired
    OurServiceRepo ourServiceRepo;

    public void serviceSave(String title,String icon_path, String short_wording,String full_wording) {
        ourServiceRepo.serviceSave(title,icon_path,short_wording,full_wording);
    }
    public void serviceUpdate(long id,String title,String icon_path, String short_wording,String full_wording) {
        ourServiceRepo.serviceUpdate(id,title,icon_path,short_wording,full_wording);
    }
    
    public List<OurServiceModel> listAll() {
        return ourServiceRepo.findAll();
    }

    public OurServiceModel findOne(long id) {
        return ourServiceRepo.findById(id).get();
    }

    public void delete(long id) {
        ourServiceRepo.deleteById(id);
    }
}
