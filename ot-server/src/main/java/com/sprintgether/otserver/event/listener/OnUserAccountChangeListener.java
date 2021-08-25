package com.sprintgether.otserver.event.listener;

import com.sprintgether.otserver.event.OnUserAccountChangeEvent;
import com.sprintgether.otserver.exception.MailSendException;
import com.sprintgether.otserver.model.entity.Mail;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.service.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
public class OnUserAccountChangeListener  implements ApplicationListener<OnUserAccountChangeEvent> {

    @Autowired
    private MailService mailService;

    private final Logger LOGGER = LogManager.getLogger(getClass());

    // Edition du compte
    @Override
    @Async
    public void onApplicationEvent(OnUserAccountChangeEvent onUserAccountChangeEvent) {
        LOGGER.error("-----------------Event change password---------------");
        sendAccountChangeEmail(onUserAccountChangeEvent);
    }

    // Edition du compte
    private void sendAccountChangeEmail(OnUserAccountChangeEvent event) {
        User user = event.getUser();
        String subject = event.getSubject();
        String content = event.getContent();
        String recipientAddress = user.getEmail();

        try{
            Mail mail = Mail.builder()
                    .subject("Account Change Mail")
                    .to(user.getEmail())
                    .content("Mot de passe modifié avec succèss")
                    .build();
            mailService.deliverWithSmtp(mail);
        }catch (MessagingException e){
            LOGGER.error(String.valueOf(e));
            throw new MailSendException(user.getEmail(), "Your password has been changed");
        }

    }
}
