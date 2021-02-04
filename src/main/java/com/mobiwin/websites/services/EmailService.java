package com.mobiwin.websites.services;

import javax.mail.MessagingException;

import com.mobiwin.websites.models.CandidateModel;
import com.mobiwin.websites.models.ContactModel;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public String sendMailContact(ContactModel contact) throws MessagingException {
        Context context = new Context();
        context.setVariable("contact", contact);

        String process = templateEngine.process("public/front/pages/email/contact", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Contact Us - Mobiwin - " + contact.getName());
        helper.setText(process, true);
        helper.setTo(contact.getEmail());
        javaMailSender.send(mimeMessage);
        return "redirect:/";
    }

    public String sendMailCandidate(CandidateModel candidate,String candidate_email,String candidate_name,String candidate_desc) throws MessagingException {
        Context context = new Context();
        context.setVariable("candidate_name", candidate_name);
        context.setVariable("candidate_email", candidate_email);
        context.setVariable("candidate_desc", candidate_desc);
        String process = templateEngine.process("public/front/pages/email/candidate", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Lamaran Kerja - Mobiwin - " + candidate_name);
        helper.setText(process, true);
        helper.setTo(candidate_email);
        javaMailSender.send(mimeMessage);
        return "redirect:/";
    }

    public String sendMailDoneCandidate(String email,String name) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", name);
        String process = templateEngine.process("public/front/pages/email/info", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Info - Mobiwin");
        helper.setText(process, true);
        helper.setTo(email);
        javaMailSender.send(mimeMessage);
        return "redirect:/";
    }
}
