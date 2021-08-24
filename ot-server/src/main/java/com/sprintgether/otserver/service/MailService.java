package com.sprintgether.otserver.service;

import com.sprintgether.otserver.model.entity.Mail;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public interface MailService {

    MimeMessageHelper prepareHelper(Mail mail, MimeMessage message) throws IOException, TemplateException, MessagingException;
    void send(Mail mail) throws IOException;
    //void deliverWithSmtp() throws MessagingException;
}
