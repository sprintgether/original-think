package com.sprintgether.otserver.event;

import com.sprintgether.otserver.model.entity.User;
import org.springframework.context.ApplicationEvent;

public class OnUserAccountChangeEvent extends ApplicationEvent {
    private User user;
    private String subject;
    private String content;

    public OnUserAccountChangeEvent(User user, String subject, String content){
        super(user);
        this.user = user;
        this.subject = subject;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
