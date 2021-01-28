package com.mobiwin.websites.controllers.back;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.AboutUsModel;
import com.mobiwin.websites.services.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
            return "public/cms/admin/pages/about/about";
        }else{
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/about/update/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void aboutUpdate(HttpServletResponse response,@RequestParam long id, @RequestParam String title , @RequestParam String summary, 
    @RequestParam String wording) {
        try{
            aboutService.update(id,title,summary,wording);
            response.sendRedirect("/admin/about/update/1");
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
