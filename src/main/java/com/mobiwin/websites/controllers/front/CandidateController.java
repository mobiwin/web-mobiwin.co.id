package com.mobiwin.websites.controllers.front;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.mobiwin.websites.models.CandidateModel;
import com.mobiwin.websites.models.CareerModel;
import com.mobiwin.websites.services.CandidateService;
import com.mobiwin.websites.services.CareerService;
import com.mobiwin.websites.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CandidateController {
    
    @Autowired
    CandidateService candidateService;

    @Autowired
    CareerService careerService;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/candidate/new/{id}", method = RequestMethod.GET)
    public String newCandidate(@PathVariable(value="id") long id,Model model) {
        model.addAttribute("title", "New Candidate");
        CareerModel candidateModel = careerService.listCareerById(id);
        model.addAttribute("candidate", candidateModel);
        return "public/front/pages/candidate/new";
    }

    @RequestMapping(value = "/candidate/save/{id}",method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String uploadFile(RedirectAttributes attributes,Model model,
            @RequestParam(value = "id_career", required = false) long id_career,
            @RequestParam(value = "candidate_desc", required = false) String candidate_desc,
            @RequestParam(value = "candidate_name", required = false) String candidate_name,
            @RequestParam(value = "candidate_email", required = false) String candidate_email	,
            @RequestParam(value = "candidate_skill", required = false) String candidate_skill,
            @RequestParam(value = "candidate_cv_path") MultipartFile candidate_cv_path,
            HttpServletRequest request,CandidateModel candidate) throws MessagingException {
        
        if (candidate_cv_path.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        try {
                // MKDIR PATH
            if (!Files.exists(Paths.get("src/main/resources/static/upload/candidate/"))) {
                Files.createDirectories(Paths.get("src/main/resources/static/upload/candidate/"));
            }
            String fileName = StringUtils.cleanPath(candidate_cv_path.getOriginalFilename());
            candidateService.saveData(id_career,candidate_desc,candidate_name,candidate_email,candidate_skill,fileName,"has_not_been_seen");
            Path path = Paths.get("src/main/resources/static/upload/candidate/" + fileName);
            Files.copy(candidate_cv_path.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            emailService.sendMailCandidate(candidate,candidate_email,candidate_name,candidate_desc);

        } catch (IOException e) {
            e.printStackTrace();
        }

        attributes.addFlashAttribute("message", "You successfully Apply");

        return "redirect:/candidate/new/{id}";
    }
}
