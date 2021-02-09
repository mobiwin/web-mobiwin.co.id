package com.mobiwin.websites.controllers.back;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.OurServiceModel;
import com.mobiwin.websites.services.OurServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
            return "cms/admin/pages/service/service";
        }else{
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/service/new", method = RequestMethod.GET)
    public String serviceNew(Model model) {
        model.addAttribute("title","New Service");
        return "cms/admin/pages/service/new";
    }

    @RequestMapping(value = "/admin/service/save", method = RequestMethod.POST)
    public String serviceSave(HttpServletResponse response,RedirectAttributes attributes,
    @RequestParam("title") String title,@RequestParam("icon_path") String icon,@RequestParam("short_wording") String short_wording,
    @RequestParam("full_wording") String full_wording) {
        try{
            OurServiceModel serviceModel = new OurServiceModel();
            serviceModel.setTitle(title);
            serviceModel.setIconPath(icon);
            serviceModel.setShortWording(short_wording);
            serviceModel.setFullWording(full_wording);
            ourServiceService.serviceSave(serviceModel);
            attributes.addFlashAttribute("msgsuc","Insert Successfully");
        } catch(Exception e){
            attributes.addFlashAttribute("msgerr",e);
        }
        return "redirect:/admin/service";
    }

    @RequestMapping(value = "/admin/service/edit/{id}", method = RequestMethod.GET)
    public String serviceEdit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("title","Edit Service");
        OurServiceModel ourServiceModel = ourServiceService.findOne(id);
        model.addAttribute("service", ourServiceModel);
        model.addAttribute("services", ourServiceService.listAll());
        return "cms/admin/pages/service/edit";
    }

    @RequestMapping(value = "/admin/service/update/{id}", method = RequestMethod.POST)
    public String serviceUpdate(RedirectAttributes attributes,HttpServletResponse response,
    @RequestParam long id,@RequestParam String title,@RequestParam String icon_path , @RequestParam String short_wording, 
    @RequestParam String full_wording) {
        try{
            OurServiceModel serviceModel = new OurServiceModel();
            serviceModel.setTitle(title);
            serviceModel.setIconPath(icon_path);
            serviceModel.setShortWording(short_wording);
            serviceModel.setFullWording(full_wording);
            ourServiceService.serviceUpdate(id,serviceModel);
            attributes.addFlashAttribute("msgsuc","Updated Successfully");
        } catch(Exception e){
            attributes.addFlashAttribute("msgerr",e);
        }
        return "redirect:/admin/service";
    }

    @RequestMapping(value = "/admin/service/delete/{id}", method = RequestMethod.GET)
    public String serviceDelete(@PathVariable("id") Integer id,RedirectAttributes attributes,
     Model model) {
         try{
            ourServiceService.delete(id);
            model.addAttribute("services", ourServiceService.listAll());
            attributes.addFlashAttribute("msgsuc","Deleted Successfully");        
         }catch(Exception e){
            attributes.addFlashAttribute("msgsuc",e);
         }
        return "redirect:/admin/service";
    }
}
