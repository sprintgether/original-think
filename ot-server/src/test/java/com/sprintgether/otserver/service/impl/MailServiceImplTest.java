package com.sprintgether.otserver.service.impl;

import com.sprintgether.otserver.model.entity.Mail;
import com.sprintgether.otserver.service.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class MailServiceImplTest {

    @Autowired
    private MailService mailService = new MailServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void deliverWithSendGrid() {
    }

    @Test
    void deliverWithSmtp() {
        Mail mail = Mail.builder()
                .to("tisix78613@ansomesa.com")
                .subject("Test Mail")
                .content("<h1> Hello guy, how are you doing this morning? </h1>")
                .build();
        try {
            mailService.deliverWithSmtp(mail);
        } catch (MessagingException e) {
            fail();
        }

    }
}