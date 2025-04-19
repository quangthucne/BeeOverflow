package com.beeoverflow.apibeeoverflow.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHashing {
    private final PasswordEncoder passwordEncoder;

    public PasswordHashing(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String passwordHash(String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
        return encodedPassword;
    }

    public boolean verifyPassword(String password, String encodedPassword) {
        boolean match = passwordEncoder.matches(password, encodedPassword);
        System.out.println("Encoded Password: " + encodedPassword);
        System.out.println("Decoded Password: " + password);
        System.out.println("Matches Password: " + match);
        return match;
    }
}
