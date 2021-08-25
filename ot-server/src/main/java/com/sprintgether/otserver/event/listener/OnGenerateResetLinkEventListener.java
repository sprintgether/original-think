package com.sprintgether.otserver.event.listener;

import com.sprintgether.otserver.event.OnGenerateResetLinkEvent;
import com.sprintgether.otserver.exception.MailSendException;
import com.sprintgether.otserver.model.entity.Mail;
import com.sprintgether.otserver.model.entity.Token;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OnGenerateResetLinkEventListener implements ApplicationListener<OnGenerateResetLinkEvent> {

    @Autowired
    @Qualifier("mailServiceImplUseSendgrid")
    private MailService mailService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    // Soumission de la requête de récupération du mot de passe
    @Override
    @Async
    public void onApplicationEvent(OnGenerateResetLinkEvent onGenerateResetLinkMailEvent) {
        sendResetLink(onGenerateResetLinkMailEvent);
    }


    // Envoi du mail de réinitialisation du mot de passe
    private void sendResetLink(OnGenerateResetLinkEvent onGenerateResetLinkEvent) {
        Token passwordResetToken = onGenerateResetLinkEvent.getPasswordResetToken();
        User user = passwordResetToken.getUser();
        String emailUser = user.getEmail();
        String emailConfirmationUrl = onGenerateResetLinkEvent.getRedirectUrl().queryParam("token", passwordResetToken.getValue())
                .toUriString();

        /*try{
            Mail mail = Mail.builder()
                    .subject("Mail de réinitialisation du mot de passe")
                    .to(emailUser)
                    .content("Veuillez cliquer sur ce <a href=" + emailConfirmationUrl + ">lien</a> pour réinitialisation votre mot de passe.")
                    .build();
            mailService.send(mail);
        }catch (IOException e){
            LOGGER.error(String.valueOf(e));
            throw new MailSendException(user.getEmail(), "Email réinitialisation");
        }*/
    }
}
