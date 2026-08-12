
package com.codeb.ims.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminTestController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> adminDashboard() {

        Map<String, Object> response = new HashMap<>();

        response.put(
                "message",
                "Admin access granted"
        );

        response.put(
                "role",
                "ADMIN"
        );

        return response;
    }
}