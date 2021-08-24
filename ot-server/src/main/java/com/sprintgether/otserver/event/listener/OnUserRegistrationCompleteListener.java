package com.sprintgether.otserver.event.listener;

import com.sprintgether.otserver.event.OnUserRegistrationCompleteEvent;
import com.sprintgether.otserver.model.entity.Mail;
import com.sprintgether.otserver.model.entity.Token;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.model.enums.EnumTokenStatus;
import com.sprintgether.otserver.model.enums.EnumTokenType;
import com.sprintgether.otserver.service.MailService;
import com.sprintgether.otserver.service.core.TokenService;
import com.sprintgether.otserver.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import com.sprintgether.otserver.exception.MailSendException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OnUserRegistrationCompleteListener implements ApplicationListener<OnUserRegistrationCompleteEvent> {

    @Value("${app.token.email.verification.duration}")
    private int emailVerificationDuration;

    @Value("${app.token.password.reset.duration}")
    private String passwordResetDuration;

    @Autowired
    @Qualifier("mailServiceImplUseSmtp") // @Qualifier("mailServiceImplUseSendgrid")
    private MailService mailService;

    @Autowired
    private TokenService tokenService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    @Async
    public void onApplicationEvent(OnUserRegistrationCompleteEvent onUserRegistrationCompleteEvent) {
        sendEmailVerificationLink(onUserRegistrationCompleteEvent);
    }

    private void sendEmailVerificationLink(OnUserRegistrationCompleteEvent onUserRegistrationCompleteEvent){
        User user = onUserRegistrationCompleteEvent.getUser();
        Token token =  tokenService.createTokenByType(
                user,
                EnumTokenType.EMAIL_VERIFICATION,
                EnumTokenStatus.PENDING,
                TokenUtil.generateRandomDigitsLettersCode(12),
                //TokenUtil.generateRandomUuid(),
                Long.valueOf(passwordResetDuration)
        );

        String emailConfirmationUrl = onUserRegistrationCompleteEvent.getRedirectUrl()
                .queryParam("token", token.getValue()).toUriString();
        try{
            System.out.println("sending mail.....");

            Mail mail = Mail.builder()
                    .subject("Activation de l'adresse mail")
                    .to(user.getEmail()) //)"pe8977461@gmail.com"
                    .content("Veuillez cliquer sur ce <a href=" + emailConfirmationUrl + ">lien</a> pour vérifier votre adresse email...............")
                    .build();
            mailService.send(mail);

            System.out.println("Done");
        }catch (IOException e){
            System.out.println("NO send mail .....");
            LOGGER.error(String.valueOf(e));
            throw new MailSendException(user.getEmail(), "Email verification"); //
        }
    }











}
