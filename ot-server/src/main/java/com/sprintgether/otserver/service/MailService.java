package com.sprintgether.otserver.service;

import com.sprintgether.otserver.model.entity.Mail;

import java.io.IOException;

public interface MailService {

    void send(Mail mail) throws IOException;

}
