package com.beeoverflow.apibeeoverflow.dto;

import com.beeoverflow.apibeeoverflow.entities.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private Integer id;
    private AccountDTO account;
    private Integer quesId;
    private String detail;
    private Boolean isDeleted;
    private List<AnsVote> ansVotes;
    private Integer parentId;
    private List<AnswerDTO> answersInParent;
    private List<ImagesAn> imagesAns;
}
