package com.beeoverflow.apibeeoverflow.controllers;

import com.beeoverflow.apibeeoverflow.beans.AnswerBean;
import com.beeoverflow.apibeeoverflow.response.Response;
import com.beeoverflow.apibeeoverflow.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/add")
    public ResponseEntity<Response> addAnswer(@Valid AnswerBean answerBean, Errors errors) {
        Response response = new Response();
        if (errors.hasErrors()) {
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            response.setStatus(0);
            response.setData(null);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(1);
            response.setMessage("success");
            response.setData(answerService.create(answerBean));
            return ResponseEntity.ok(response);
        }

    }

    @PostMapping("/update")
    public ResponseEntity<Response> updateAnswer(@Valid AnswerBean answerBean, Errors errors) {
        Response response = new Response();
        if (errors.hasErrors()) {
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            response.setStatus(0);
            response.setData(null);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(1);
            response.setMessage("success");
            response.setData(answerService.update(answerBean));
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> deleteAnswer(int id) {
        Response response = new Response();
            response.setStatus(1);
            response.setMessage("success");
            answerService.delete(id);
            response.setData(null);
            return ResponseEntity.ok(response);
        
    }
}
