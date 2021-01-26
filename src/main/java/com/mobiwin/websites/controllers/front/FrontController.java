package com.mobiwin.websites.controllers.front;

import java.util.List;

import com.mobiwin.websites.models.AboutUsModel;
import com.mobiwin.websites.models.CareerModel;
import com.mobiwin.websites.models.CarouselModel;
import com.mobiwin.websites.models.OurClientModel;
import com.mobiwin.websites.models.OurProjectModel;
import com.mobiwin.websites.models.OurServiceModel;
import com.mobiwin.websites.models.OurTeamModel;
import com.mobiwin.websites.models.TestimonyModel;
import com.mobiwin.websites.services.AboutUsService;
import com.mobiwin.websites.services.CareerService;
import com.mobiwin.websites.services.CarouselService;
import com.mobiwin.websites.services.OurClientService;
import com.mobiwin.websites.services.OurProjectService;
import com.mobiwin.websites.services.OurServiceService;
import com.mobiwin.websites.services.OurTeamService;
import com.mobiwin.websites.services.TestimonyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String Index(Model model) {

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
        model.addAttribute("carrer", carrer);
        // End Carrer

        // Teams
        List<OurTeamModel> team = ourTeamService.listAllTeam();
        model.addAttribute("team", team);
        // End Teams

        return "public/front/index";
    }
}
