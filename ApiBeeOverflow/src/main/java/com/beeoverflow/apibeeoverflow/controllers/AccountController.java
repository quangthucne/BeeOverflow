package com.beeoverflow.apibeeoverflow.controllers;

import com.beeoverflow.apibeeoverflow.beans.AccountBean;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.response.Response;
import com.beeoverflow.apibeeoverflow.service.AccountService;
import com.beeoverflow.apibeeoverflow.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ImageService imageService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody AccountBean accountBean, Errors errors) {

        Response response = new Response();

        if (errors.hasErrors()) {
            response.setStatus(1);
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            Account saveAccount = accountService.create(accountBean);
            response.setStatus(1);
            response.setMessage("Account created");
            response.setData(saveAccount);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody AccountBean accountBean, Errors errors) {

        Response response = new Response();

        String message = accountService.verifyAccount(accountBean);

        response.setStatus(1);
        response.setMessage(message);
        response.setData(accountBean);

        return ResponseEntity.ok(response);
    }
}
