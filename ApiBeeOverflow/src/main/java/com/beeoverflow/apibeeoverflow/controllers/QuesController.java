package com.beeoverflow.apibeeoverflow.controllers;

import com.beeoverflow.apibeeoverflow.beans.ImageBean;
import com.beeoverflow.apibeeoverflow.beans.QuestionBean;
import com.beeoverflow.apibeeoverflow.beans.TagBean;
import com.beeoverflow.apibeeoverflow.entities.Question;
import com.beeoverflow.apibeeoverflow.mappers.QuestionMapper;
import com.beeoverflow.apibeeoverflow.response.Response;
import com.beeoverflow.apibeeoverflow.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuesController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("")
    public ResponseEntity<Response> getQuestions() {
        Response response = new Response();
        response.setData(questionService.getQuestions());
        response.setMessage("Success");
        response.setStatus(1);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> addQuestion(@Valid QuestionBean questionBean, @Valid TagBean tagBean, Errors errors) {

        Response response = new Response();
        if (errors.hasErrors()) {
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            response.setStatus(1);
            response.setData(null);
            return ResponseEntity.ok(response);
        }
        else {
            Question newQues = questionService.createQuestion(questionBean, tagBean);
            response.setMessage("Success");
            response.setStatus(1);
            response.setData(newQues);
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Response> updateQuestion(@Valid QuestionBean questionBean, Errors errors) {
        Response response = new Response();
        Question newQues = questionService.updateQuestion(questionBean);
        response.setMessage("Success");
        response.setStatus(1);
        response.setData(newQues);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> deleteQuestion(@Valid QuestionBean questionBean, Errors errors) {
        Response response = new Response();
        Question delQues = questionService.deleteQuestion(questionBean);
        response.setMessage(delQues.getIsDeleted()?"Success":"Failed");
        response.setStatus(1);
        response.setData(delQues);
        return ResponseEntity.ok(response);
    }
}
