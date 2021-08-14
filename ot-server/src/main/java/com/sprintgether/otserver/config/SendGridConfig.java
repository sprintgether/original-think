package com.sprintgether.otserver.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    @Value("${app.mail.sendgrid.key}")
    private String sendGridKey;

    @Bean
    public SendGrid getSendGrid(){
        return new SendGrid(sendGridKey);
    }
}
