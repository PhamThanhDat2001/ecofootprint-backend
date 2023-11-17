package com.doan.ecofootprint_be.service;

import com.doan.ecofootprint_be.entity.RegistrationUserToken;
import com.doan.ecofootprint_be.entity.Users;
import com.doan.ecofootprint_be.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.doan.ecofootprint_be.repository.RegistrationUserTokenRepository;
import com.doan.ecofootprint_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Transactional
@Service
public class UserService implements  IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private RegistrationUserTokenRepository registrationUserTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void createUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        createNewRegistrationUserToken(user);
        // send email to confirm
        sendConfirmUserRegistrationViaEmail(user.getEmail());
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
    public void activeUser(String token) {
        RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);

        Users user = registrationUserToken.getUsers();
        user.setStatus(Users.UserStatus.ACTIVE);

        userRepository.save(user);

        // remove Registration User Token
        registrationUserTokenRepository.deleteById(registrationUserToken.getId());
    }

    public void sendConfirmUserRegistrationViaEmail(String email) {
        eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
    }

}
