package com.beeoverflow.apibeeoverflow.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beeoverflow.apibeeoverflow.beans.OrderBean;
import com.beeoverflow.apibeeoverflow.entities.Document;
import com.beeoverflow.apibeeoverflow.entities.Order;
import com.beeoverflow.apibeeoverflow.entities.OrderDetail;
import com.beeoverflow.apibeeoverflow.jpas.OrderDetailJPA;
import com.beeoverflow.apibeeoverflow.jpas.OrderJPA;
import com.beeoverflow.apibeeoverflow.utils.CookiesUtil;
import com.beeoverflow.apibeeoverflow.utils.JwtUtil;


@Service
public class OrderService {
    @Autowired
    OrderJPA orderJPA;

    @Autowired
    OrderDetailJPA orderDetailJPA;

    @Autowired
    AccountService accountService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CookiesUtil cookiesUtil;

    @Autowired
    DocumentService documentService;

    public OrderDetail createOrder (OrderBean orderBean) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Account Id: " + jwtUtil.extractUserId(cookiesUtil.getToken()));
        int accountId = jwtUtil.extractUserId(cookiesUtil.getToken());
        Document doc = documentService.getDocById(orderBean.getDocId());
        OrderDetail orderDetail = new OrderDetail();
        Order order = new Order();
        
        order.setAccount(accountService.getAccountById(accountId));
        Order saveOrder = orderJPA.save(order);

        orderDetail.setOrder(saveOrder);
        orderDetail.setCreatedDate(now);
        orderDetail.setDoc(doc);
        orderDetail.setPrice(doc.getDocDetail().getPrice());

        OrderDetail saveOrderDetail = orderDetailJPA.save(orderDetail);
        return saveOrderDetail;
    }
}
