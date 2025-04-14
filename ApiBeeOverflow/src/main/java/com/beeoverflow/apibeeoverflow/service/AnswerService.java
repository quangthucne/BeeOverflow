package com.beeoverflow.apibeeoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beeoverflow.apibeeoverflow.jpas.AnswerJPA;

@Service
public class AnswerService {
    
    @Autowired
    AnswerJPA answerJPA;

    
}
