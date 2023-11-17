package com.doan.ecofootprint_be.service;

import org.springframework.stereotype.Component;

@Component
public interface IEmailService {
    void sendRegistrationUserConfirm(String email);
}
