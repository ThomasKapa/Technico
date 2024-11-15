package com.technicoCompany.technico.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

    public boolean authorize(String role, String action) {
        if ("ADMIN".equals(role)) {
            return true;
        } else
            return false;
    }





}
