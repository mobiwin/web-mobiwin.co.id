package com.mobiwin.websites.controllers.back;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mobiwin.websites.common.MD5Util;
import com.mobiwin.websites.models.AdminModel;
import com.mobiwin.websites.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(HttpSession sessi) {
        if(sessi.getAttribute("id_session") != null){
            return "redirect:/admin/dashboard";
        }else{
            return "public/cms/admin/login";
        }
        
    }

    @RequestMapping(value = "/masuk", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(Model sendDataToPublic, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam Map<String, String> body) {

        if (body.get("password") != "") {

            try {
                var idUser = "";
                var nameUser = "";

                List<AdminModel> loginUserData = adminService.serviceUserPassword(MD5Util.string2MD5(body.get("password")),body.get("username"));
                int countData = loginUserData.size();

                for (AdminModel udata : loginUserData) {
                    idUser = udata.getId().toString();
                    nameUser = udata.getUserName();
                }

                sendDataToPublic.addAttribute("name_user_nya", nameUser);

                if (countData > 0) {
                    sessi.setAttribute("id_session", idUser);
                    sessi.setAttribute("name_session", nameUser);

                    httpResponse.sendRedirect("/admin/dashboard");
                    return null;
                } else {
                    sendDataToPublic.addAttribute("msg", "Password atau username salah atau tidak ditemukan");
                }
            } catch (Exception e) {
                System.out.println(e);
                sendDataToPublic.addAttribute("msg", e);
            }
        } else {
            sendDataToPublic.addAttribute("msg", "data tidak boleh kosong");
        }
        return "public/cms/admin/login";
    }

    

    @RequestMapping(value = "/keluar", method = RequestMethod.GET)
    public String logout(Model sendDataToPublic, HttpSession sessi, HttpServletResponse httpResponse) {

        try {
            if (sessi.getAttribute("id_session") != null) {
                sessi.removeAttribute("id_session");
            }

            httpResponse.sendRedirect("/admin");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            sendDataToPublic.addAttribute("msg", e);
        }
        return null;
    }
    
}
