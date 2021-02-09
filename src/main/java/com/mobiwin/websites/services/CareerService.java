package com.mobiwin.websites.services;

import java.util.List;

import com.mobiwin.websites.models.CareerModel;
import com.mobiwin.websites.repositories.CareerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerService {

    @Autowired
    CareerRepo careerRepo;


    public List<CareerModel> listAllCareer() {
        return careerRepo.findAll();
    }

    public CareerModel listCareerById(Long id) {
        return careerRepo.findById(id).get();
    }

    public void saveCareer(CareerModel ourCareerModel) {
        careerRepo.save(ourCareerModel);
    }

    public void updatePartDataCareer(String valTitle, String valPos, String valReq, String valDesc, String valId) {
        careerRepo.repoUpdate(valTitle, valPos, valReq, valDesc, valId);;
    }

    public void deleteCareer(Long id) {
        careerRepo.deleteById(id);
    }
}
