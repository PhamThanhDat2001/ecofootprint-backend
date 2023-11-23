package com.doan.ecofootprint_be.service;

import com.doan.ecofootprint_be.entity.Users;
import com.doan.ecofootprint_be.repository.RegistrationUserTokenRepository;
import com.doan.ecofootprint_be.repository.ResetPasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{
    @Autowired
    private IUserService userService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RegistrationUserTokenRepository registrationUserTokenRepository;
    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;
    @Override
    public void sendRegistrationUserConfirm(String email) {
        Users user = userService.findUserByEmail(email);
        String token = registrationUserTokenRepository.findByUserId(user.getId());

        String confirmationUrl = "http://localhost:8080/api/v1/user/activeUser?token=" + token;

        String subject = "Xác Nhận Đăng Ký Account";
        String content = "Bạn đã đăng ký thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
                + confirmationUrl;

        sendEmail(email, subject, content);
    }

    @Override
    public void sendResetPassword(String email) {
        Users user = userService.findUserByEmail(email);
        String token = resetPasswordTokenRepository.findByUserId(user.getId());

        String confirmationUrl = "http://localhost:3000/auth/new-password/" + token;

        String subject = "Thiết lập lại mật khẩu";
        String content = "Click vào link dưới đây để thiết lập lại mật khẩu (nếu không phải bạn xin vui lòng bỏ qua).\n"
                + confirmationUrl;

        sendEmail(email, subject, content);
    }

    private void sendEmail(final String recipientEmail, final String subject, final String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }
}
