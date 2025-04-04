package com.beeoverflow.apibeeoverflow.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashing {
    public String passwordHash(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
        return encodedPassword;
    }

    public boolean verifyPassword(String password, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Encoded Password: " + encodedPassword);
        System.out.println("Decoded Password: " + password);
        System.out.println("matches Password: " + encoder.matches(password, encodedPassword));
        return encoder.matches(password, encodedPassword);
    }
}
