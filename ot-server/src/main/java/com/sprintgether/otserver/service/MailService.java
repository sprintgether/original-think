package com.sprintgether.otserver.service;

import com.sprintgether.otserver.model.entity.Mail;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
<<<<<<< HEAD
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public interface MailService {

    MimeMessageHelper prepareHelper(Mail mail, MimeMessage message) throws IOException, TemplateException, MessagingException;

    void send(Mail mail) throws IOException; //, TemplateException, MessagingException;
=======
import java.io.IOException;

public interface MailService {
    void send(Mail mail) throws IOException;
    void deliverWithSmtp() throws MessagingException;
>>>>>>> 10d6b18e2c66a195513d2e52b18d614452256e1b
}
