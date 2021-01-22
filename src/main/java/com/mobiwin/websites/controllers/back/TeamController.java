package com.mobiwin.websites.controllers.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TeamController {
    
    @RequestMapping(value = "/admin/team", method = RequestMethod.GET)
    public String listTeam() {

        return "public/cms/admin/pages/ourteam/list";
    }

    @RequestMapping(value = "/admin/team/new", method = RequestMethod.GET)
    public String newTeam() {

        return "public/cms/admin/pages/ourteam/new";
    }
}
