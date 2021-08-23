package com.sprintgether.otserver.service;

import com.sprintgether.otserver.model.entity.Mail;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailService {
    void send(Mail mail) throws IOException;
    void deliverWithSmtp() throws MessagingException;
}
