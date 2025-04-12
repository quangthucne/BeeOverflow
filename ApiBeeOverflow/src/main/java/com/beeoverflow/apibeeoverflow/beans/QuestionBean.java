package com.beeoverflow.apibeeoverflow.beans;

import com.beeoverflow.apibeeoverflow.entities.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBean {

    private int id;

    private int accountId;

    @NotBlank
    private String title;

    @NotBlank
    private String detail;

    private List<Tag> tags;

    private List<MultipartFile> images;

    private boolean isCheck;

    private boolean isDeleted;
}
