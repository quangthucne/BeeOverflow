package com.beeoverflow.apibeeoverflow.jpas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beeoverflow.apibeeoverflow.entities.OrderDetail;

public interface OrderDetailJPA  extends JpaRepository<OrderDetail, Integer>{
    
}
