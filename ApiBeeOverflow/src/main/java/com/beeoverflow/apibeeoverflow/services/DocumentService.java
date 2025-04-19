package com.beeoverflow.apibeeoverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beeoverflow.apibeeoverflow.beans.DocumentBean;
import com.beeoverflow.apibeeoverflow.entities.DocumentDetail;
import com.beeoverflow.apibeeoverflow.jpas.DocumentDetailJPA;
import com.beeoverflow.apibeeoverflow.jpas.DocumentJPA;
import com.beeoverflow.apibeeoverflow.entities.Document;
import com.beeoverflow.apibeeoverflow.entities.DocumentDetail;

@Service
public class DocumentService {
    
    @Autowired
    DocumentJPA documentJPA;

    @Autowired
    DocumentDetailJPA documentDetailJPA;

    @Autowired
    AccountService accountService;

    public List<DocumentDetail> getAllDoc () {
        return documentDetailJPA.findAll();
    }

    public DocumentDetail createDoc(DocumentBean documentBean){
        Document doc = new Document();
        DocumentDetail docDetail = new DocumentDetail();

        docDetail.setNameDoc(documentBean.getName());
        docDetail.setDescription(documentBean.getDescription());
        docDetail.setDocSrc(documentBean.getDocUrl());
        docDetail.setIsDeleted(false);
        docDetail.setPrice(documentBean.getPrice());
        docDetail.setFree(documentBean.isFree());
        
        DocumentDetail saveDocDetail = documentDetailJPA.save(docDetail);

        doc.setAccount(accountService.getAccountById(6));
        doc.setDocDetail(saveDocDetail);

        documentJPA.save(doc);

        return saveDocDetail;
    }

    public DocumentDetail update(DocumentBean documentBean) {
        DocumentDetail docDetail = documentDetailJPA.findById(documentBean.getId());

        docDetail.setNameDoc(documentBean.getName());
        docDetail.setDescription(documentBean.getDescription());
        docDetail.setDocSrc(documentBean.getDocUrl());
        docDetail.setIsDeleted(false);
        docDetail.setPrice(documentBean.getPrice());
        docDetail.setFree(documentBean.isFree());
        
        DocumentDetail saveDocDetail = documentDetailJPA.save(docDetail);

        return saveDocDetail;
    }

    public DocumentDetail deleted(int id) {
        DocumentDetail documentDetail = documentDetailJPA.findById(id);
        documentDetail.setIsDeleted(true);
        return documentDetailJPA.save(documentDetail);
    }
}
