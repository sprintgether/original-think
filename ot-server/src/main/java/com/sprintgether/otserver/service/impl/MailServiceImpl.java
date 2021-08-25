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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LogManager.getLogger(MailServiceImpl.class);

    @Value("${app.mail.sendgrid.sender}")
    private String sender;

    @Value("${app.mail.smtp.host}")
    private String host;

    @Value("${app.mail.smtp.username}")
    private String username;

    @Value("${app.mail.smtp.password}")
    private String password;

    @Value("${app.mail.smtp.port}")
    private String port;

    @Value("${app.mail.smtp.auth}")
    private boolean auth;

    @Value("${app.mail.smtp.start-tls-enable}")
    private boolean startTlsEnable;

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private SendGrid sendGrid;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public MimeMessageHelper prepareHelper(Mail mail, MimeMessage message) throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.setTo(mail.getTo());
        mimeMessageHelper.setText(mail.getContent(), true);
        mimeMessageHelper.setSubject(mail.getSubject());
        mimeMessageHelper.setFrom(mail.getFrom());

        return  mimeMessageHelper;
    }

    @Override
    public void deliverWithSendGrid(Mail mail) throws IOException {
        com.sendgrid.helpers.mail.objects.Email from = new com.sendgrid.helpers.mail.objects.Email(sender);
        String subject = mail.getSubject();
        com.sendgrid.helpers.mail.objects.Email to = new com.sendgrid.helpers.mail.objects.Email(mail.getTo());
        Content content = new Content("text/html", mail.getContent());
        com.sendgrid.helpers.mail.Mail sendGridMail = new com.sendgrid.helpers.mail.Mail(from, subject, to, content);

        Request request = new Request();

        LOGGER.debug("Envoi de mail avec Sendgrid");
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(sendGridMail.build());

        Response response = sendGrid.api(request);
        LOGGER.info("Request code: {}, Request body: {}, Requet headers: {}"
                , response.getStatusCode(), response.getBody(), response.getHeaders());

        LOGGER.debug("Sauvegarder le mail envoyé dans la base de données");
        mail.setState(EnumMailState.SENDED);
        mail.setSendedAt(Instant.now());
        mailRepository.save(mail);
    }

    @Override
    public void deliverWithSmtp(Mail mail) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", startTlsEnable);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(mail.getTo()));
        message.setSubject(mail.getSubject());
        message.setContent(mail.getContent(), "text/html;charset=utf-8");
        Transport.send(message);
    }
}
