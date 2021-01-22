package com.mobiwin.websites.services;


import java.util.List;

import com.mobiwin.websites.models.CarouselModel;
import com.mobiwin.websites.repositories.CarouselRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CarouselService {

    @Autowired
    CarouselRepo carouselRepo;

    public void sliderSave(String nameImg,String caption) {
        carouselRepo.sliderSave(nameImg,caption);
    }

    public void sliderUpdate(long id,String nameImg,String caption) {
        carouselRepo.sliderUpdate(id,nameImg,caption);
    }

    public List<CarouselModel> listAll() {
        return carouselRepo.findAll();
    }

    public CarouselModel findOne(long id) {
        return carouselRepo.findById(id).get();
    }

    public void delete(long id) {
        carouselRepo.deleteById(id);
    }
}
