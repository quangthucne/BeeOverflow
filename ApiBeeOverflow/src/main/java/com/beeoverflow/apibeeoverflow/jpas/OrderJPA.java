package com.beeoverflow.apibeeoverflow.jpas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beeoverflow.apibeeoverflow.entities.Order;

public interface OrderJPA extends JpaRepository<Order, Integer> {
    
}
