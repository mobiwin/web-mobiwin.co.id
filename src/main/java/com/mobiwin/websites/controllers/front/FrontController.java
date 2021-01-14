package com.mobiwin.websites.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index() {

        return "public/front/index";
    }
}
