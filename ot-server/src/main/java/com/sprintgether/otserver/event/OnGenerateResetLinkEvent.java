package com.sprintgether.otserver.event;

import com.sprintgether.otserver.model.entity.Token;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;


public class OnGenerateResetLinkEvent extends ApplicationEvent {
    private UriComponentsBuilder redirectUrl;
    private Token passwordResetToken;

    public OnGenerateResetLinkEvent(Token token, UriComponentsBuilder redirectUrl){
        super(token);
        this.passwordResetToken = token;
        this.redirectUrl = redirectUrl;
    }

    public UriComponentsBuilder getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(UriComponentsBuilder redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Token getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(Token passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }
}
