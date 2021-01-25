package com.mobiwin.websites.services;

import com.mobiwin.websites.models.OurProjectModel;
import com.mobiwin.websites.repositories.OurProjectRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OurProjectService {

    @Autowired
    OurProjectRepo ourProjectRepo;

    public void projectUpdate(long id,String title,String kind,String client,String technology,String nameImg) {
        ourProjectRepo.projectUpdate(id,title,kind,client,technology,nameImg);
    }
    public void projectUpdateWithOutImg(long id,String title,String kind,String client,String technology) {
        ourProjectRepo.projectUpdateWithOutImg(id,title,kind,client,technology);
    }
    public void saveProject(OurProjectModel ourProjectModel) {
        ourProjectRepo.save(ourProjectModel);
    }
    public List<OurProjectModel> listAll() {
        return ourProjectRepo.findAll();
    }

    public OurProjectModel findOne(long id) {
        return ourProjectRepo.findById(id).get();
    }

    public void delete(long id) {
        ourProjectRepo.deleteById(id);
    }
}
