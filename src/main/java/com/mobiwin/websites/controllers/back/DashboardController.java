package com.mobiwin.websites.controllers.back;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mobiwin.websites.models.AdminModel;
import com.mobiwin.websites.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public String dashboard(Model sendDataToPublic, HttpSession sessi, HttpServletResponse httpResponse) {

        try {
            if (sessi.getAttribute("id_session") != null) {
                List<AdminModel> loadUsersData = adminService
                        .serviceListUsersExcept(sessi.getAttribute("id_session").toString());
                sendDataToPublic.addAttribute("title", "Dashboard");
                sendDataToPublic.addAttribute("list_user", loadUsersData);
                sendDataToPublic.addAttribute("username", sessi.getAttribute("name_session"));

                return "public/cms/admin/pages/dashboard/dashboard";
            } else {
                httpResponse.sendRedirect("/admin/");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            sendDataToPublic.addAttribute("msg", e);
            return "public/cms/admin/pages/dashboard/dashboard";
        }
    }
}
