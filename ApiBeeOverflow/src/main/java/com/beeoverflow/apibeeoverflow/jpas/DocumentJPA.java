package com.beeoverflow.apibeeoverflow.jpas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beeoverflow.apibeeoverflow.entities.Document;



public interface DocumentJPA extends JpaRepository<Document, Integer> {
    
}
