package com.mobiwin.websites.services;

import java.util.List;


import com.mobiwin.websites.models.AboutUsModel;
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
    
    public void update(long id,String title, String summary, String wording) {
        // aboutUsRepo.save(about);
        aboutUsRepo.updateSql(id,title,summary,wording);
    }

    public List<AboutUsModel> findAll() {
        return aboutUsRepo.findAll();
    }

    public AboutUsModel findOne(long id) {
        return aboutUsRepo.findById(id).get();
    }
}
