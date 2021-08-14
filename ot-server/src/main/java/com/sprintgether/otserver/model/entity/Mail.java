package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprintgether.otserver.model.enums.EnumMailState;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ot_mail")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mail extends MainEntity {

    @Column(name = "mail_from")
    private String from;

    @Column(name = "mail_to")
    private String to;

    @Enumerated(EnumType.STRING)
    private EnumMailState state;

    private Instant sendedAt;

    private String subject;
    private String content;

}
