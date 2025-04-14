package com.beeoverflow.apibeeoverflow.beans;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.entities.Answer;
import com.beeoverflow.apibeeoverflow.entities.Question;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerBean {

    private int id;

    private int accountId;

    private int questionId;

    @NotBlank
    private String detail;

    private List<MultipartFile> images;

    private Boolean isDeleted;

    private int parentId;

}
