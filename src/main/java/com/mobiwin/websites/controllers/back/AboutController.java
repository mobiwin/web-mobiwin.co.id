package com.mobiwin.websites.controllers.back;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.AboutUsModel;
import com.mobiwin.websites.repositories.AboutUsRepo;
import com.mobiwin.websites.services.AboutUsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;


@Controller
public class AboutController {
    @Autowired
    AboutUsService aboutService;
    // @RequestMapping(value = "/admin/about", method = RequestMethod.GET)
    // public String About() {
    //     return "public/cms/admin/pages/about/about";
    // }
    @RequestMapping(value = "/admin/about/{id}")
    public String about(@PathVariable("id") Integer id, Model model){
        AboutUsModel aboutUsModel = aboutService.findOne(id);
        model.addAttribute("about", aboutUsModel);
        model.addAttribute("abouts", aboutService.findAll());
        return "public/cms/admin/pages/about/about";
    }
    // @RequestMapping(value = "/save_about", method = RequestMethod.POST)
    // public String insert(@ModelAttribute("aboutUsModel") AboutUsModel aboutUsModel){
    //     aboutService.create(aboutUsModel);
    //     return "redirect:/admin/about/1";
    // }
    @RequestMapping(value = "/about_update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void about_update(HttpServletResponse response,@RequestParam long id, @RequestParam String title , @RequestParam String summary, 
    @RequestParam String wording) {
        try{
            aboutService.update(id,title,summary,wording);
            response.sendRedirect("/admin/about/1");
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
}
