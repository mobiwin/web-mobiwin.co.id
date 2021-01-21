package com.mobiwin.websites.controllers.back;

import java.util.List;

import com.mobiwin.websites.models.CarouselModel;
import com.mobiwin.websites.repositories.CarouselRepo;
import com.mobiwin.websites.services.CarouselService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SliderController {

    @Autowired
    CarouselService carouselService;
    
    @Autowired
    CarouselRepo carouselRepo;
    
    @RequestMapping(value = "/admin/slider")
    public String slider(Model model) {
        model.addAttribute("title", "Sliders");
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
