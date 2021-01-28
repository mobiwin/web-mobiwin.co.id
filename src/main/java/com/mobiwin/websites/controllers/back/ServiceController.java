package com.mobiwin.websites.controllers.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.OurServiceModel;
import com.mobiwin.websites.services.OurServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class ServiceController {

    @Autowired
    OurServiceService ourServiceService;

    @RequestMapping(value = "/admin/service", method = RequestMethod.GET)
    public String service(Model model,HttpSession sessi) {
        if (sessi.getAttribute("id_session") != null) {
            model.addAttribute("title","Services");
            List<OurServiceModel> getData = ourServiceService.listAll();
            model.addAttribute("service", getData);
            return "public/cms/admin/pages/service/service";
        }else{
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/service/new", method = RequestMethod.GET)
    public String serviceNew(Model model) {
        model.addAttribute("title","New Service");
        return "public/cms/admin/pages/service/new";
    }

    @RequestMapping(value = "/admin/service/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void serviceSave(HttpServletResponse response,@RequestParam String title,@RequestParam String icon_path , @RequestParam String short_wording, 
    @RequestParam String full_wording) {
        try{
            ourServiceService.serviceSave(title,icon_path,short_wording,full_wording);
            response.sendRedirect("/admin/service");
        } catch(IOException e){
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/admin/service/edit/{id}", method = RequestMethod.GET)
    public String serviceEdit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("title","Edit Service");
        OurServiceModel ourServiceModel = ourServiceService.findOne(id);
        model.addAttribute("service", ourServiceModel);
        model.addAttribute("services", ourServiceService.listAll());
        return "public/cms/admin/pages/service/edit";
    }

    @RequestMapping(value = "/admin/service/update/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void serviceUpdate(HttpServletResponse response, @RequestParam long id,@RequestParam String title,@RequestParam String icon_path , @RequestParam String short_wording, 
    @RequestParam String full_wording) {
        try{
            ourServiceService.serviceUpdate(id,title,icon_path,short_wording,full_wording);
            response.sendRedirect("/admin/service");
        } catch(IOException e){
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/admin/service/delete/{id}", method = RequestMethod.GET)
    public String serviceDelete(@PathVariable("id") Integer id,
     Model model) {
        ourServiceService.delete(id);
        model.addAttribute("services", ourServiceService.listAll());
        return "redirect:/admin/service";
    }
}
