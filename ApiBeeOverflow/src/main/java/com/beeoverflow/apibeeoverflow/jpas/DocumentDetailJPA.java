package com.beeoverflow.apibeeoverflow.jpas;

import org.springframework.data.jpa.repository.JpaRepository;
import com.beeoverflow.apibeeoverflow.entities.DocumentDetail;
import java.util.List;


public interface DocumentDetailJPA extends JpaRepository<DocumentDetail, Integer> {
    DocumentDetail findById(int id);
}
