package com.beeoverflow.apibeeoverflow.controllers;

import com.beeoverflow.apibeeoverflow.beans.AccountBean;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.response.Response;
import com.beeoverflow.apibeeoverflow.services.ImageService;
import com.beeoverflow.apibeeoverflow.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ImageService imageService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid AccountBean accountBean, Errors errors) {

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

        String token = accountService.verifyAccount(accountBean);
        if (token != null) {
            response.setStatus(1);
            response.setMessage("Đăng nhập thành công");
            response.setData(token);
        } else {
            response.setStatus(0);
            response.setMessage("Tên đăng nhập hoặc mật khẩu không chính xác!");
            response.setData(null);
        }


        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Response> getAllAccounts() {
        Response response = new Response();
        response.setStatus(1);
        response.setMessage("All accounts");
        response.setData(accountService.getAccounts());
        return ResponseEntity.ok(response);
    }
}
