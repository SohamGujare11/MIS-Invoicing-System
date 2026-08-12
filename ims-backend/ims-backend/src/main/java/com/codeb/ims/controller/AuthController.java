package com.codeb.ims.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeb.ims.dto.LoginRequest;
import com.codeb.ims.dto.LoginResponse;
import com.codeb.ims.dto.RegisterRequest;
import com.codeb.ims.dto.RegisterResponse;
import com.codeb.ims.dto.ResetPasswordRequest;
import com.codeb.ims.dto.VerifyEmailResponse;
import com.codeb.ims.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response =
                authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }

    // =========================
    // VERIFY EMAIL
    // =========================

    @GetMapping("/verify-email")
    public ResponseEntity<VerifyEmailResponse> verifyEmail(
            @RequestParam String token) {

        VerifyEmailResponse response =
                authService.verifyEmail(token);

        return ResponseEntity.ok(response);
    }

    // =========================
    // FORGOT PASSWORD
    // =========================

    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");

        authService.forgotPassword(email);

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Password reset request created successfully. Please check your email."
                )
        );
    }

    // =========================
    // RESET PASSWORD
    // =========================

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request) {

        authService.resetPassword(request);

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Password reset successfully. You can now login."
                )
        );
    }
}