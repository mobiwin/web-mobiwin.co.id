package com.mobiwin.websites.services;

import javax.mail.MessagingException;

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

    public String sendMail(ContactModel contact) throws MessagingException {
        Context context = new Context();
        context.setVariable("contact", contact);

        String process = templateEngine.process("public/front/pages/email/contact", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome " + contact.getName());
        helper.setText(process, true);
        helper.setTo(contact.getEmail());
        javaMailSender.send(mimeMessage);
        return "redirect:/";
    }
}
