package com.beeoverflow.apibeeoverflow.dto;

import com.beeoverflow.apibeeoverflow.entities.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Integer id;
    private AccountDTO account;
    private String title;
    private String detail;
    private Boolean isDeleted;
    private Boolean isCheck;
    private List<AnswerDTO> answers;
    private List<QuestionVote> questionVotes;
    private List<Tag> tags;
    private List<ImagesQue> imagesQues;
    private LocalDateTime createdDate;

}
