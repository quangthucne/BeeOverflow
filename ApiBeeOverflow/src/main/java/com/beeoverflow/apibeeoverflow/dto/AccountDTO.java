package com.beeoverflow.apibeeoverflow.dto;

import com.beeoverflow.apibeeoverflow.entities.Reputation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer id;

    private String username;

    private String fullname;

    private String avatar;

    private Integer point;

    private Boolean isActive;

    private Reputation reputation;
}
