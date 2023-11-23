package com.doan.ecofootprint_be.service;

import com.doan.ecofootprint_be.entity.Users;
import com.doan.ecofootprint_be.form.ChangePasswordForm;
import com.doan.ecofootprint_be.form.ForgetPasswordForm;
import com.doan.ecofootprint_be.form.ForgetPasswordRequestForm;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends UserDetailsService {
    void createUser(Users user);
    public Users getUserById(int id);
    Users findUserByEmail(String email);

    public Users getAccountByUsername(String username);
    void activeUser(String token);
    void sendConfirmUserRegistrationViaEmail(String email);

    void resetPasswordViaEmail(ForgetPasswordRequestForm form);
    void sendResetPasswordViaEmail(String email);

    void resetPassword(ForgetPasswordForm form);

    boolean changePassword(int id, ChangePasswordForm form);
}
