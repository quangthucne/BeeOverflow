package com.beeoverflow.apibeeoverflow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beeoverflow.apibeeoverflow.beans.DocumentBean;
import com.beeoverflow.apibeeoverflow.entities.DocumentDetail;
import com.beeoverflow.apibeeoverflow.response.Response;
import com.beeoverflow.apibeeoverflow.services.DocumentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin("*")
@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    DocumentService documentService;

    @GetMapping("")
    public ResponseEntity<Response> getDocument() {
        Response response = new Response();
        response.setMessage("Danh sách tài liệu");
        response.setStatus(1);
        response.setData(documentService.getAllDoc());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addDocument(@Valid @RequestBody DocumentBean documentBean, Errors errors){
        Response response = new Response();
        if (errors.hasErrors()){
            response.setData(documentBean);
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            response.setStatus(0);
        } else {
            response.setStatus(1);
            response.setMessage("Tải lên thành công!");
            response.setData(documentService.createDoc(documentBean));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Response> update(@Valid @RequestBody DocumentBean documentBean, Errors errors) {
        Response response = new Response();

        if (errors.hasErrors()){
            response.setData(documentBean);
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            response.setStatus(0);
        } else {
            response.setMessage("Cập nhật thành công!");
            response.setStatus(1);
            response.setData(documentService.update(documentBean));
        }
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> deleted(@RequestBody DocumentBean documentBean, Errors errors) {
        Response response = new Response();
        if (errors.hasErrors()){
            response.setData(documentBean);
            response.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
            response.setStatus(0);
        } else {
            response.setMessage("Xóa thành công!");
            response.setStatus(1);
            response.setData(documentService.deleted(documentBean.getId()));
        }
        return ResponseEntity.ok(response);
    }
    
    
    
}
