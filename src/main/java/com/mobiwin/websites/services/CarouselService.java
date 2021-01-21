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

    public void save(CarouselModel carouselModel) {
        carouselRepo.save(carouselModel);
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
