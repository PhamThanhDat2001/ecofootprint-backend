package com.doan.ecofootprint_be.event;

import org.springframework.context.ApplicationEvent;

public class OnSendRegistrationUserConfirmViaEmailEvent extends ApplicationEvent {
    private String email;

    public OnSendRegistrationUserConfirmViaEmailEvent(String email) {
        super(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
