package com.codeb.ims.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/protected")
    public Map<String, Object> protectedEndpoint(
            Authentication authentication) {

        Map<String, Object> response = new HashMap<>();

        response.put("message", "JWT authentication successful");
        response.put("email", authentication.getName());
        response.put("authorities",
                authentication.getAuthorities());

        return response;
    }
}