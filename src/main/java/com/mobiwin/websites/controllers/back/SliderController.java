package com.mobiwin.websites.controllers.back;
import java.util.List;
import com.mobiwin.websites.models.CarouselModel;
import com.mobiwin.websites.repositories.CarouselRepo;
import com.mobiwin.websites.services.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SliderController {

    @Autowired
    CarouselService carouselService;
    
    @Autowired
    CarouselRepo carouselRepo;
    
    @RequestMapping(value = "/admin/slider",method = RequestMethod.GET)
    public String slider(Model model) {
        model.addAttribute("title", "Sliders");
        List<CarouselModel> tes = carouselService.listAll();
        model.addAttribute("carousel", tes);
        return "public/cms/admin/pages/slider/slider";
    }

    @RequestMapping(value = "/admin/slider/new",method = RequestMethod.GET)
    public String sliderNew() {
        return "public/cms/admin/pages/slider/new";
    }

    @RequestMapping(value = "/admin/slider/edit/{id}",method = RequestMethod.GET)
    public String sliderEdit(@PathVariable("id") Integer id, Model model) {
        CarouselModel carouselModel = carouselService.findOne(id);
        model.addAttribute("carousel", carouselModel);
        model.addAttribute("carousels", carouselService.listAll());
        return "public/cms/admin/pages/slider/edit";
    }

    @RequestMapping(value = "/admin/slider/delete/{id}",method = RequestMethod.DELETE)
    public String sliderDelete(@PathVariable("id") Integer id, Model model) {
        carouselService.delete(id);
        model.addAttribute("carousels", carouselService.listAll());
        return "redirect:/admin/slider";
    }
}
