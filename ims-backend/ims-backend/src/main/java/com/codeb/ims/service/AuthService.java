package com.codeb.ims.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeb.ims.dto.LoginRequest;
import com.codeb.ims.dto.LoginResponse;
import com.codeb.ims.dto.RegisterRequest;
import com.codeb.ims.dto.RegisterResponse;
import com.codeb.ims.dto.ResetPasswordRequest;
import com.codeb.ims.dto.VerifyEmailResponse;
import com.codeb.ims.entity.User;
import com.codeb.ims.repository.UserRepository;
import com.codeb.ims.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            EmailService emailService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    // =========================
    // REGISTRATION
    // =========================

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email is already registered"
            );
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        user.setEnabled(true);
        user.setEmailVerified(false);

        String verificationToken =
                UUID.randomUUID().toString();

        user.setVerificationToken(verificationToken);

        userRepository.save(user);

        emailService.sendVerificationEmail(
                user.getEmail(),
                user.getFullName(),
                verificationToken
        );

        return new RegisterResponse(
                "Registration successful. "
                + "Please check your email to verify your account.",
                user.getEmail()
        );
    }

    // =========================
    // LOGIN
    // =========================

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow(() ->
                new IllegalArgumentException(
                        "Invalid email or password"
                )
        );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new IllegalArgumentException(
                    "Invalid email or password"
            );
        }

        if (!user.isEnabled()) {
            throw new IllegalArgumentException(
                    "User account is disabled"
            );
        }

        if (!user.isEmailVerified()) {
            throw new IllegalArgumentException(
                    "Please verify your email before logging in"
            );
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponse(
                token,
                "Bearer",
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    // =========================
    // EMAIL VERIFICATION
    // =========================

    public VerifyEmailResponse verifyEmail(String token) {

        User user = userRepository
                .findByVerificationToken(token)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid or expired verification token"
                        )
                );

        user.setEmailVerified(true);
        user.setVerificationToken(null);

        userRepository.save(user);

        return new VerifyEmailResponse(
                "Email verified successfully. "
                + "You can now login."
        );
    }

    // =========================
    // FORGOT PASSWORD
    // =========================

    public void forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No account found with this email"
                        )
                );

        String resetToken =
                UUID.randomUUID().toString();

        LocalDateTime expiry =
                LocalDateTime.now().plusMinutes(15);

        user.setResetPasswordToken(resetToken);
        user.setResetPasswordTokenExpiry(expiry);

        userRepository.save(user);

        emailService.sendPasswordResetEmail(
                user.getEmail(),
                user.getFullName(),
                resetToken
        );
    }

    // =========================
    // RESET PASSWORD
    // =========================

    public void resetPassword(
            ResetPasswordRequest request) {

        User user = userRepository
                .findByResetPasswordToken(request.getToken())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid or expired reset token"
                        )
                );

        if (user.getResetPasswordTokenExpiry() == null ||
                user.getResetPasswordTokenExpiry()
                        .isBefore(LocalDateTime.now())) {

            throw new IllegalArgumentException(
                    "Password reset token has expired"
            );
        }

        if (request.getNewPassword() == null ||
                request.getNewPassword().length() < 8) {

            throw new IllegalArgumentException(
                    "Password must contain at least 8 characters"
            );
        }

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );

        // Token can only be used once
        user.setResetPasswordToken(null);
        user.setResetPasswordTokenExpiry(null);

        userRepository.save(user);
    }
}