package com.mobiwin.websites.controllers.back;

import java.io.IOException;
import java.util.List;

import com.mobiwin.websites.models.CarouselModel;
import com.mobiwin.websites.repositories.CarouselRepo;
import com.mobiwin.websites.services.CarouselService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class SliderController {

    @Autowired
    CarouselService carouselService;
    
    @Autowired
    CarouselRepo carouselRepo;
    
    @RequestMapping(value = "/admin/slider")
    public String slider(Model model) {
        List<CarouselModel> tes = carouselService.listAll();
        model.addAttribute("carousel", tes);

        return "public/cms/admin/pages/slider/slider";
    }

    @GetMapping("/admin/slider/new")
    public String create() {
        return "public/cms/admin/pages/slider/new";
    }

    // @RequestMapping(value = "/admin/slider/save", method = RequestMethod.POST)
    // public String save(@ModelAttribute("carousel") CarouselModel carouselModel, 
    // @RequestParam("image") MultipartFile multipartFile) throws IOException {
    //     // carouselService.save(carouselModel);
    // }

    @RequestMapping(value = "/admin/slider/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        CarouselModel carouselModel = carouselService.findOne(id);
        model.addAttribute("carousel", carouselModel);
        model.addAttribute("carousels", carouselService.listAll());
        return "public/cms/admin/pages/slider/edit";
    }

    @RequestMapping(value = "/admin/slider/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){
        carouselService.delete(id);
        model.addAttribute("carousels", carouselService.listAll());
        return "redirect:/admin/slider";
    }
}
