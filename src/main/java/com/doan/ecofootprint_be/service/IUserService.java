package com.doan.ecofootprint_be.service;

import com.doan.ecofootprint_be.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends UserDetailsService {
    void createUser(Users user);
    public Users getUserById(int id);
    Users findUserByEmail(String email);

    public Users getAccountByUsername(String username);
    void activeUser(String token);
    void sendConfirmUserRegistrationViaEmail(String email);
}
