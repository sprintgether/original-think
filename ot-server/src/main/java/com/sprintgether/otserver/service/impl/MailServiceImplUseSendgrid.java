package com.sprintgether.otserver.service.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Content;
import com.sprintgether.otserver.model.entity.Mail;
import com.sprintgether.otserver.model.enums.EnumMailState;
import com.sprintgether.otserver.repository.MailRepository;
import com.sprintgether.otserver.service.MailService;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.Instant;

@Service("mailServiceImplUseSendgrid")
@Slf4j
public class MailServiceImplUseSendgrid implements MailService {

    private static final Logger LOGGER = LogManager.getLogger(MailServiceImplUseSendgrid.class);

    @Value("${app.mail.sendgrid.sender}")
    private String sender;

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private SendGrid sendGrid;

    @Override
    public void send(Mail mail) throws IOException {

        com.sendgrid.helpers.mail.objects.Email from = new com.sendgrid.helpers.mail.objects.Email(sender);
        String subject = mail.getSubject();
        com.sendgrid.helpers.mail.objects.Email to = new com.sendgrid.helpers.mail.objects.Email(mail.getTo());
        Content content = new Content("text/html", mail.getContent());
        com.sendgrid.helpers.mail.Mail sendGridMail = new com.sendgrid.helpers.mail.Mail(from, subject, to, content);

        Request request = new Request();

        // Envoyer le mail avec SendGrid
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(sendGridMail.build());

        Response response = sendGrid.api(request);
        LOGGER.info("Request code: {}, Request body: {}, Requet headers: {}"
                , response.getStatusCode(), response.getBody(), response.getHeaders());

        // Sauvegarder le mail envoyé dans la base de données
        mail.setState(EnumMailState.SENDED);
        mail.setSendedAt(Instant.now());
        mailRepository.save(mail);
    }

    @Override
    public MimeMessageHelper prepareHelper(Mail mail, MimeMessage message) throws IOException, TemplateException, MessagingException {
        return null;
    }
}
