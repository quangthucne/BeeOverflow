package com.beeoverflow.apibeeoverflow.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beeoverflow.apibeeoverflow.beans.OrderBean;
import com.beeoverflow.apibeeoverflow.entities.OrderDetail;
import com.beeoverflow.apibeeoverflow.response.Response;
import com.beeoverflow.apibeeoverflow.services.OrderService;
import com.beeoverflow.apibeeoverflow.services.PaymentService;

import jakarta.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/payment")
public class PaymentController {
    
    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @PostMapping("")
    public ResponseEntity<Response>  getPayment(@Valid @RequestBody OrderBean orderBean, Errors errors) throws UnsupportedEncodingException, UnknownHostException {
        Response response = new Response();
        if (errors.hasErrors()) {
            response.setStatus(0);
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            response.setData(orderBean);
        } else {
            OrderDetail orderDetail = orderService.createOrder(orderBean);
            response.setStatus(1);
            response.setData(null);
            response.setMessage(paymentService.getPayment(String.valueOf(orderDetail.getId()), orderDetail.getPrice()));
        }
        return ResponseEntity.ok(response);
    }


    
}
