package com.mobiwin.websites.services;

import java.util.List;

import com.mobiwin.websites.models.TestimonyModel;
import com.mobiwin.websites.repositories.TestimonyRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonyService {

    @Autowired
    TestimonyRepo testimonyRepo;

    public void testimonyUpdate(long id,String name_user,String company,String testimony_text,String nameImg) {
        testimonyRepo.testimonyUpdate(id,name_user,company,testimony_text,nameImg);
    }
    public void testimonyUpdateWithOutImg(long id,String name_user,String company,String testimony_text) {
        testimonyRepo.testimonyUpdateWithOutImg(id,name_user,company,testimony_text);
    }
    public void saveTestimony(TestimonyModel testimonyModel) {
        testimonyRepo.save(testimonyModel);
    }
    public List<TestimonyModel> listAll() {
        return testimonyRepo.findAll();
    }

    public TestimonyModel findOne(long id) {
        return testimonyRepo.findById(id).get();
    }

    public void delete(long id) {
        testimonyRepo.deleteById(id);
    }
}
