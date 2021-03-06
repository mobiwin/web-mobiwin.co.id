package com.mobiwin.websites.controllers.front;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobiwin.websites.models.AboutUsModel;
import com.mobiwin.websites.models.CareerModel;
import com.mobiwin.websites.models.CarouselModel;
import com.mobiwin.websites.models.ContactModel;
import com.mobiwin.websites.models.OurClientModel;
import com.mobiwin.websites.models.OurProjectModel;
import com.mobiwin.websites.models.OurServiceModel;
import com.mobiwin.websites.models.OurTeamModel;
import com.mobiwin.websites.models.TestimonyModel;
import com.mobiwin.websites.services.AboutUsService;
import com.mobiwin.websites.services.CareerService;
import com.mobiwin.websites.services.CarouselService;
import com.mobiwin.websites.services.ContactService;
import com.mobiwin.websites.services.EmailService;
import com.mobiwin.websites.services.OurClientService;
import com.mobiwin.websites.services.OurProjectService;
import com.mobiwin.websites.services.OurServiceService;
import com.mobiwin.websites.services.OurTeamService;
import com.mobiwin.websites.services.TestimonyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FrontController {
    
    @Autowired
    CarouselService carouselService;

    @Autowired
    AboutUsService aboutService;
    
    @Autowired
    OurServiceService ourServiceService;

    @Autowired
    OurProjectService ourProjectService;

    @Autowired
    TestimonyService testimonyService;

    @Autowired
    OurClientService ourClientService;

    @Autowired
    CareerService carrerService;

    @Autowired
    OurTeamService ourTeamService;

    @Autowired
    ContactService contactService;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index(Model model) {
        model.addAttribute("title", "PT. Muloska Pratama | Mobiwin");
        model.addAttribute("keyword", "mobiwin,muloska pratama,bintaro");
        model.addAttribute("description", "Muloska Pratama known as MOBIWIN is an Information Technology Services (IT) company in Indonesia. Our commitment is to help our client optimize their information technology functions by improving the effectiveness and efficiency of our client’s business.");
        model.addAttribute("url", "www.mobiwin.co.id");
        model.addAttribute("image", "/img/logo/logo-title.png");
        // Carousel
        List<CarouselModel> carousel = carouselService.listAll();
        model.addAttribute("carousel", carousel);
        //End Carousel

        // About Us 
        List<AboutUsModel> about = aboutService.findAll();
        model.addAttribute("about", about);
        // End About Us

        // Service 
        List<OurServiceModel> service = ourServiceService.listAll();
        model.addAttribute("service", service);
        // End Service

        // Project / Portofolio 
        List<OurProjectModel> project = ourProjectService.listAll();
        model.addAttribute("project", project);
        // End Project / Portofolio

        // Testimonials 
        List<TestimonyModel> testimony = testimonyService.listAll();
        model.addAttribute("testimony", testimony);
        // End Testimonials

        // Client 
        List<OurClientModel> client = ourClientService.listAllClient();
        model.addAttribute("client", client);
        // End Client

        // Carrer 
        List<CareerModel> carrer = carrerService.listAllCareer();
        model.addAttribute("career", carrer);
        // End Carrer

        // Teams
        List<OurTeamModel> team = ourTeamService.listAllTeam();
        model.addAttribute("team", team);
        // End Teams

        return "front/index";
    }

    @RequestMapping(value = "/detail/about/{id}", method = RequestMethod.GET)
    public String aboutDetail(@PathVariable("id") Integer id, Model model,HttpServletRequest req) {

        model.addAttribute("title", "About Mobiwin");
        AboutUsModel aboutUsModel = aboutService.findOne(id);
        model.addAttribute("about", aboutUsModel);
        model.addAttribute("keyword", aboutUsModel.getTitle());
        model.addAttribute("description", aboutUsModel.getWording());
        model.addAttribute("url", req.getRequestURI());
        model.addAttribute("image", "/img/logo/logo-title.png");
        return "front/pages/detail/about";
    }

    @RequestMapping(value = "/detail/service/{id}", method = RequestMethod.GET)
    public String serviceDetail(@PathVariable("id") Integer id, Model model,HttpServletRequest req) {

        OurServiceModel ourServiceModel = ourServiceService.findOne(id);
        model.addAttribute("service", ourServiceModel);
        model.addAttribute("title", ourServiceModel.getTitle()+" | Mobiwin");
        model.addAttribute("keyword", ourServiceModel.getTitle());
        model.addAttribute("description", ourServiceModel.getFullWording());
        model.addAttribute("url", req.getRequestURI());
        model.addAttribute("image", "/img/logo/logo-title.png");
        return "front/pages/detail/service";
    }

    @RequestMapping(value = "/detail/portofolio/{id}", method = RequestMethod.GET)
    public String portofolioDetail(@PathVariable("id") Integer id, Model model , HttpServletRequest req) {

        OurProjectModel ourProjectModel = ourProjectService.findOne(id);
        model.addAttribute("project", ourProjectModel);
        model.addAttribute("title", ourProjectModel.getProjectTitle()+" | Mobiwin");
        model.addAttribute("keyword", ourProjectModel.getProjectTitle()+","+ourProjectModel.getKind());
        model.addAttribute("description", ourProjectModel.getTechnology());
        model.addAttribute("url", req.getRequestURI());
        model.addAttribute("image", ourProjectModel.getPreviewPath());
        return "front/pages/detail/portofolio";
    }

    @RequestMapping(value = "/detail/career/{id}", method = RequestMethod.GET)
    public String careerDetail(@PathVariable("id") long id, Model model,HttpServletRequest req) {

        CareerModel careerModel = carrerService.listCareerById(id);
        model.addAttribute("career", careerModel);
        model.addAttribute("title", careerModel.getJobTitle()+" | Mobiwin");
        model.addAttribute("keyword", careerModel.getJobTitle()+","+careerModel.getPotition());
        model.addAttribute("description", careerModel.getPotitionDesc());
        model.addAttribute("url", req.getRequestURI());
        model.addAttribute("image", careerModel.getIconOf());
        return "front/pages/detail/career";
    }

    @RequestMapping(value = "/detail/team/{id}", method = RequestMethod.GET)
    public String teamDetail(@PathVariable("id") long id, Model model,HttpServletRequest req) {

        OurTeamModel ourTeamModel = ourTeamService.listTeamById(id);
        model.addAttribute("team", ourTeamModel);
        model.addAttribute("title", ourTeamModel.getEmployeeName()+ " - " + ourTeamModel.getPotition()+" | Mobiwin");
        model.addAttribute("keyword", ourTeamModel.getEmployeeName()+","+ourTeamModel.getInstagram());
        model.addAttribute("description", ourTeamModel.getBio());
        model.addAttribute("url", req.getRequestURI());
        model.addAttribute("image", ourTeamModel.getAvatarPath());
        return "front/pages/detail/team";
    }

    @RequestMapping(value = "/contact/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void contactSave(HttpServletResponse response,RedirectAttributes attributes,
    @RequestParam String name, @RequestParam String email , @RequestParam String departement, 
    @RequestParam String pesan,ContactModel contact) throws MessagingException {
        
        try{
            emailService.sendMailContact(contact);
            contactService.contactSave(name,email,departement,"Contact Us - Mobiwin",pesan);
            attributes.addFlashAttribute("message", "Thank you for the advice");
            response.sendRedirect("/#contact");
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
