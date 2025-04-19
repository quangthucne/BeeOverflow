package com.beeoverflow.apibeeoverflow.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AuthenticationManager;


import java.util.HashMap;
import java.util.Map;

@Component
public class CookiesUtil {

    @Autowired
    HttpServletRequest req;

    @Autowired
    HttpServletResponse resp;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    public void setCookie(int id, int role) {

        Cookie accountId = new Cookie("accountId", String.valueOf(id));
        Cookie accountRole = new Cookie("role", String.valueOf(role));

        accountId.setPath("/");
        accountRole.setPath("/");

        accountId.setMaxAge(60 * 60 * 24 * 7);
        accountRole.setMaxAge(60 * 60 * 24 * 7);

        resp.addCookie(accountId);
        resp.addCookie(accountRole);

    }

    public Map<String, String> getCookie() {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accountId")) {
                    String accountId = cookie.getValue();
                    cookieMap.put("accountId", accountId);
                } else if (cookie.getName().equals("accountRole")) {
                    String accountRole = cookie.getValue();
                    cookieMap.put("accountRole", accountRole);
                }
            }
        }
        return cookieMap;
    }

    public String setToken(String username, String id, String role) {
        try {
            // generated token jwt
            String token = jwtUtil.generateToken(username, id, role);
            Cookie jwtCookie = new Cookie("jwt", token);

            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(60 * 60 * 10);

            resp.addCookie(jwtCookie);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Authentication error: " + e.getMessage());
        }
    }

    public String getToken() {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
