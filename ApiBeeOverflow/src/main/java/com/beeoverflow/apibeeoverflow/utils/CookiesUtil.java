package com.beeoverflow.apibeeoverflow.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CookiesUtil {

    @Autowired
    HttpServletRequest req;

    @Autowired
    HttpServletResponse resp;


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

}
