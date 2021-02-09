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

    public void serviceSave(OurServiceModel serviceModel) {
        ourServiceRepo.save(serviceModel);
        // ourServiceRepo.serviceSave(title,icon_path,short_wording,full_wording);
    }
    public void serviceUpdate(long id,OurServiceModel serviceModel) {
        // ourServiceRepo.serviceUpdate(id,title,icon_path,short_wording,full_wording);
        serviceModel.setId(id);
        ourServiceRepo.save(serviceModel);
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
