package com.doan.ecofootprint_be.service;

import com.doan.ecofootprint_be.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserService {
    void createUser(Users user);

    Users findUserByEmail(String email);

    void activeUser(String token);
    void sendConfirmUserRegistrationViaEmail(String email);
}
