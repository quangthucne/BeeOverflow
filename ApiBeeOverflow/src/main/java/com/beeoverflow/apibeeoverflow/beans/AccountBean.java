package com.beeoverflow.apibeeoverflow.beans;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Mutability;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBean {

    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    private String fullname;

    @NotBlank
    private String email;


    private String avatar;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    private int gender;

    private Integer point;
    private Boolean isActive;

    private MultipartFile avatarFile;


}
