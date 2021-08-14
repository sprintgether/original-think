package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprintgether.otserver.model.enums.EnumTokenStatus;
import com.sprintgether.otserver.model.enums.EnumTokenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ot_token")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token extends MainEntity{

    private String value;

    @Enumerated(EnumType.STRING)
    private EnumTokenType tokenType;

    @Enumerated(EnumType.STRING)
    private EnumTokenStatus tokenStatus;

    private Instant expiryDate;

    @OneToOne(targetEntity = User.class)
    private User user;

    // Vérification du token
    public void setVerified() {
        setTokenStatus(EnumTokenStatus.VERIFIED);
    }

}
