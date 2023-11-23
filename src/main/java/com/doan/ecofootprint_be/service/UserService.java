package com.doan.ecofootprint_be.service;

//import com.doan.ecofootprint_be.controller.CustomExceptionHandler;
import com.doan.ecofootprint_be.entity.CustomUserDetail;
import com.doan.ecofootprint_be.entity.RegistrationUserToken;
import com.doan.ecofootprint_be.entity.ResetPasswordToken;
import com.doan.ecofootprint_be.entity.Users;
import com.doan.ecofootprint_be.event.OnResetPasswordViaEmailEvent;
import com.doan.ecofootprint_be.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.doan.ecofootprint_be.form.ChangePasswordForm;
import com.doan.ecofootprint_be.form.ForgetPasswordForm;
import com.doan.ecofootprint_be.form.ForgetPasswordRequestForm;
import com.doan.ecofootprint_be.repository.RegistrationUserTokenRepository;
import com.doan.ecofootprint_be.repository.ResetPasswordTokenRepository;
import com.doan.ecofootprint_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Transactional
@Service
@RequiredArgsConstructor
public class UserService implements  IUserService{
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final RegistrationUserTokenRepository registrationUserTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users users = userRepository.findByUsername(username);
        if(users == null) {
            throw new UsernameNotFoundException(username);
        }
        if (!isUserActive(username)) {
//            throw new CustomExceptionHandler("User is not active");
//            return
//            return (UserDetails) ResponseEntity
//                    .status(HttpStatus.UNAUTHORIZED)
//                    .body("Products deleted successfully.");
            throw new AccessDeniedException("User is not active");
        }
        return new CustomUserDetail(users);
    }
    @Override
    public void createUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        createNewRegistrationUserToken(user);
        // send email to confirm
        sendConfirmUserRegistrationViaEmail(user.getEmail());
    }

    @Override
    public Users getUserById(int id) {
        return userRepository.findById(id).get();
    }

    private void createNewRegistrationUserToken(Users user) {

        // create new token for confirm Registration
        final String newToken = UUID.randomUUID().toString();
        RegistrationUserToken token = new RegistrationUserToken(newToken, user);

        registrationUserTokenRepository.save(token);
    }
    @Override
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users getAccountByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void activeUser(String token) {
        RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);

        Users user = registrationUserToken.getUsers();
        user.setStatus(Users.UserStatus.ACTIVE);
        user.setActive(true);
        userRepository.save(user);

        // remove Registration User Token
        registrationUserTokenRepository.deleteById(registrationUserToken.getId());
    }

    public void sendConfirmUserRegistrationViaEmail(String email) {
        eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
    }

    @Override
    public void resetPasswordViaEmail(ForgetPasswordRequestForm form) {
// find user by email
        Users user = findUserByEmail(form.getEmail());

        // remove token token if exists
        resetPasswordTokenRepository.deleteByUserId(user.getId());

        // create new reset password token
        createNewResetPasswordToken(user);

        // send email
        sendResetPasswordViaEmail(form.getEmail());
    }
    private void createNewResetPasswordToken(Users user) {

        // create new token for Reseting password
        final String newToken = UUID.randomUUID().toString();
        ResetPasswordToken token = new ResetPasswordToken(newToken, user);

        resetPasswordTokenRepository.save(token);
    }
    @Override
    public void sendResetPasswordViaEmail(String email) {
        eventPublisher.publishEvent(new OnResetPasswordViaEmailEvent(email));
    }

    @Override
    public void resetPassword(ForgetPasswordForm form) {
        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(form.getToken());

        // change password
        Users user = resetPasswordToken.getUsers();
        user.setPassword(passwordEncoder.encode(form.getNewPassword()));
        userRepository.save(user);

        // remove Reset Password
        resetPasswordTokenRepository.deleteById(resetPasswordToken.getId());

    }

    @Override
    public boolean changePassword(int id, ChangePasswordForm form) {
        Users user = userRepository.findById(id).orElse(null);
        if (user != null && passwordEncoder.matches(form.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(form.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean isUserActive(String username) {
        Users user = userRepository.findByUsername(username);
        return user != null && user.isActive();
    }
}
