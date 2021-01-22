package com.mobiwin.websites.controllers.back;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.mobiwin.websites.models.CarouselModel;
import com.mobiwin.websites.repositories.CarouselRepo;
import com.mobiwin.websites.services.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.commons.io.FilenameUtils;
@Controller
public class SliderController {

    @Autowired
    CarouselService carouselService;
    
    @Autowired
    CarouselRepo carouselRepo;

    private final String UPLOAD_DIR = "./src/main/resources/static/front/img/slider/";

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

    @RequestMapping(value = "/admin/slider/save",method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String sliderSave(@RequestParam("carouselImage") MultipartFile carouselImage,@RequestParam("caption") String caption,RedirectAttributes attributes,Model model) {

        if (carouselImage.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }
        String fileName = StringUtils.cleanPath(carouselImage.getOriginalFilename());
        String nameImg = UPLOAD_DIR + fileName;
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(carouselImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            carouselService.sliderSave(nameImg,caption);
        } catch (IOException e) {
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/admin/slider";
    }

    @RequestMapping(value = "/admin/slider/update{id}",method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String sliderUpdate(@RequestParam("carouselImage") MultipartFile carouselImage,
    @RequestParam("id") long id,@RequestParam("caption") String caption,RedirectAttributes attributes,Model model) {

        if (carouselImage.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }
        String fileName = StringUtils.cleanPath(carouselImage.getOriginalFilename());
        String nameImg = UPLOAD_DIR + fileName;
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(carouselImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            carouselService.sliderUpdate(id,nameImg,caption);
        } catch (IOException e) {
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/admin/slider";
    }

    @RequestMapping(value = "/admin/slider/edit/{id}",method = RequestMethod.GET)
    public String sliderEdit(@PathVariable("id") Integer id, Model model){
        CarouselModel carouselModel = carouselService.findOne(id);
        model.addAttribute("carousel", carouselModel);
        model.addAttribute("carousels", carouselService.listAll());
        return "public/cms/admin/pages/slider/edit";
    }

    @RequestMapping(value = "/admin/slider/delete/{id}",method = RequestMethod.GET)
    public String sliderDelete(@PathVariable("id") Integer id, Model model){
        carouselService.delete(id);
        model.addAttribute("carousels", carouselService.listAll());
        return "redirect:/admin/slider";
    }
}
