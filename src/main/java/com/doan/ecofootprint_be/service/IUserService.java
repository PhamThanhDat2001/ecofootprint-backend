package com.doan.ecofootprint_be.service;

import com.doan.ecofootprint_be.entity.Users;
import com.doan.ecofootprint_be.form.ChangePasswordForm;
import com.doan.ecofootprint_be.form.ForgetPasswordForm;
import com.doan.ecofootprint_be.form.ForgetPasswordRequestForm;
import com.doan.ecofootprint_be.form.FormUpdating;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends UserDetailsService {
//public interface IUserService  {
    void createUser(Users user);
    public Users getUserById(int id);
    Users findUserByEmail(String email);
    Users findUserByUserName(String username);
    public Users getAccountByUsername(String username);
    void activeUser(String token);
    void sendConfirmUserRegistrationViaEmail(String email);

    void resetPasswordViaEmail(ForgetPasswordRequestForm form);
    void sendResetPasswordViaEmail(String email);

    void resetPassword(ForgetPasswordForm form);

    boolean changePassword(String username, ChangePasswordForm form);

    boolean existsUserByEmail(String email);

    boolean existsUserByUserName(String username);
    void updateGroup(int id, FormUpdating form);
}
