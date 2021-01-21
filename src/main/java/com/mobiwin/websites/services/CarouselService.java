package com.mobiwin.websites.services;

import java.util.ArrayList;
import java.util.List;

import com.mobiwin.websites.models.CarouselModel;
import com.mobiwin.websites.repositories.CarouselRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
