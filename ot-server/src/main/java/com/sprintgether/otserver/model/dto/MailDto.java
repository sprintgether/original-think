package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Mail;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MailDto {
    private String to;
    private String subject;
    private String content;

    public static MailDto fromEntity(Mail mail){
        if(mail == null){
            return null;
        }

        return MailDto.builder()
                .to(mail.getTo())
                .subject(mail.getSubject())
                .content(mail.getContent())
                .build();
    }

    public static Mail toEntity(MailDto mailDto){
        if(mailDto == null){
            return null;
        }

        Mail mail = new Mail();
        mail.setTo(mailDto.getTo());
        mail.setSubject(mailDto.getSubject());
        mail.setContent(mailDto.getContent());

        return mail;
    }
}
