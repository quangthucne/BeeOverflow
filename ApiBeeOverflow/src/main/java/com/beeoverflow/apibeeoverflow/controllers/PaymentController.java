package com.beeoverflow.apibeeoverflow.controllers;

import org.springframework.web.bind.annotation.*;

import com.beeoverflow.apibeeoverflow.beans.OrderBean;
import com.beeoverflow.apibeeoverflow.beans.ResultPaymentBean;
import com.beeoverflow.apibeeoverflow.entities.DocumentDetail;
import com.beeoverflow.apibeeoverflow.entities.OrderDetail;
import com.beeoverflow.apibeeoverflow.response.Response;
import com.beeoverflow.apibeeoverflow.services.DocumentService;
import com.beeoverflow.apibeeoverflow.services.OrderService;
import com.beeoverflow.apibeeoverflow.services.PaymentService;

import jakarta.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {
    
    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @Autowired
    DocumentService documentService;

    @PostMapping("")
    public ResponseEntity<Response>  getPayment(@Valid @RequestBody OrderBean orderBean, Errors errors) throws UnsupportedEncodingException, UnknownHostException {
        Response response = new Response();
        if (errors.hasErrors()) {
            response.setStatus(0);
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            response.setData(orderBean);
        } else {
            // OrderDetail orderDetail = orderService.createOrder(orderBean);
            DocumentDetail docDetail = documentService.getDocById(orderBean.getDocId()).getDocDetail();
            response.setStatus(1);
            response.setData(null);
            response.setMessage(paymentService.getPayment(String.valueOf(docDetail.getId()), docDetail.getPrice()));
        }
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/return")
    public ResponseEntity<Response> getResult(@ModelAttribute ResultPaymentBean resultPaymentBean) {
        Response response = new Response();
        System.out.println("Return Code: " + resultPaymentBean.getVnp_ResponseCode());
        System.out.println("Return Tx: " + resultPaymentBean.getVnp_TxnRef());
        ResultPaymentBean result  = paymentService.convertData(resultPaymentBean);
        OrderBean orderBean = new OrderBean();
        orderBean.setDocId(getIdFromVnp_TxnRef(result.getVnp_TxnRef()));

        if (result.getVnp_ResponseCode().equals("00")) {
            orderService.createOrder(orderBean);
        }
        response.setMessage("kết quả giao dịch");
        response.setStatus(1);
        response.setData(orderBean);
        return ResponseEntity.ok(response);
    }

    public ResultPaymentBean mapData(String vnp_Amount, String vnp_PayDate, String vnp_ResponseCode, String vnp_TxnRef, String vnp_TransactionStatus) {
        ResultPaymentBean result = new ResultPaymentBean();
        result.setVnp_Amount(vnp_Amount);
        result.setVnp_PayDate(vnp_PayDate);
        result.setVnp_ResponseCode(vnp_ResponseCode);
        result.setVnp_TransactionStatus(vnp_TransactionStatus);
        result.setVnp_TxnRef(vnp_TxnRef);
        return result;
    }

    public int getIdFromVnp_TxnRef(String vnp_TxnRef) {
        String[] split = vnp_TxnRef.split("_");
        return Integer.parseInt(split[split.length - 1]);
    }

    public int convertPrice (String price) {
        String cleanedPrice = price.replaceAll("[^\\d]", "");
        return Integer.parseInt(cleanedPrice);
    }
    
}
