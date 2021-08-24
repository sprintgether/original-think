package com.sprintgether.otserver.service.impl;

import com.sprintgether.otserver.model.entity.Mail;
import com.sprintgether.otserver.service.MailService;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service("mailServiceImplUseSmtp")
@Slf4j
public class MailServiceImplUseSmtp implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public MimeMessageHelper prepareHelper(Mail mail, MimeMessage message) throws IOException, TemplateException, MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.setTo(mail.getTo());
        mimeMessageHelper.setText(mail.getContent(), true);
        mimeMessageHelper.setSubject(mail.getSubject());
        mimeMessageHelper.setFrom(mail.getFrom());

        return  mimeMessageHelper;
    }

    @Override
    public void send(Mail mail) throws IOException{ //, TemplateException, MessagingException {
        /*MimeMessage message = javaMailSender.createMimeMessage();
        prepareHelper(mail, message);
        javaMailSender.send(message);*/

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getTo());

        msg.setSubject(mail.getSubject());
        msg.setText(mail.getContent());

        javaMailSender.send(msg);
    }
}
