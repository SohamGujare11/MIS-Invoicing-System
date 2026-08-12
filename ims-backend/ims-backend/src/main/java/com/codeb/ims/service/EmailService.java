package com.codeb.ims.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${app.frontend.url}")
    private String frontendUrl;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // =========================
    // EMAIL VERIFICATION
    // =========================

    public void sendVerificationEmail(
            String recipientEmail,
            String fullName,
            String verificationToken) {

        String verificationLink =
                frontendUrl
                + "/verify-email?token="
                + verificationToken;

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setFrom(senderEmail);
        message.setTo(recipientEmail);

        message.setSubject(
                "Verify Your MIS Invoicing System Account"
        );

        message.setText(
                "Hello " + fullName + ",\n\n"
                + "Thank you for registering with the MIS "
                + "Invoicing System.\n\n"
                + "Please click the link below to verify your email:\n\n"
                + verificationLink
                + "\n\n"
                + "If you did not create this account, "
                + "please ignore this email.\n\n"
                + "Regards,\n"
                + "MIS Invoicing System"
        );

        mailSender.send(message);
    }

    // =========================
    // PASSWORD RESET EMAIL
    // =========================

    public void sendPasswordResetEmail(
            String recipientEmail,
            String fullName,
            String resetToken) {

        String resetLink =
                frontendUrl
                + "/reset-password?token="
                + resetToken;

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setFrom(senderEmail);
        message.setTo(recipientEmail);

        message.setSubject(
                "Reset Your MIS Invoicing System Password"
        );

        message.setText(
                "Hello " + fullName + ",\n\n"
                + "We received a request to reset your "
                + "MIS Invoicing System password.\n\n"
                + "Click the link below to reset your password:\n\n"
                + resetLink
                + "\n\n"
                + "This link will expire in 15 minutes.\n\n"
                + "If you did not request a password reset, "
                + "please ignore this email.\n\n"
                + "Regards,\n"
                + "MIS Invoicing System"
        );

        mailSender.send(message);
    }
}