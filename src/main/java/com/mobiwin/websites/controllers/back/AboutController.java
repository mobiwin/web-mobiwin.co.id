package com.mobiwin.websites.controllers.back;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.AboutUsModel;
import com.mobiwin.websites.services.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
public class AboutController {

    @Autowired
    AboutUsService aboutService;

    @RequestMapping(value = "/admin/about/edit/{id}", method = RequestMethod.GET)
    public String about(@PathVariable("id") Integer id, Model model,HttpSession sessi) {
        if (sessi.getAttribute("id_session") != null) {
            model.addAttribute("title", "About Us");
            AboutUsModel aboutUsModel = aboutService.findOne(id);
            model.addAttribute("about", aboutUsModel);
            model.addAttribute("abouts", aboutService.findAll());
            return "cms/admin/pages/about/about";
        }else{
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/about/update/{id}", method = RequestMethod.POST)
    public String aboutUpdate(RedirectAttributes attributes,
    @RequestParam long id, AboutUsModel about) {
        try{
            aboutService.update(id,about);
            attributes.addFlashAttribute("message","Insert Successfully");
        } catch(Exception e){
            System.out.println(e);
        }
        return "redirect:/admin/about/edit/1";
    }
}
