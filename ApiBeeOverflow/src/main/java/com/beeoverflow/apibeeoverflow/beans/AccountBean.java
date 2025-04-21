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

    @NotBlank(message = "Tên đăng nhập không được bỏ trống")
    private String username;

    @NotBlank(message = "Họ và tên không được bỏ tống")
    private String fullname;

    @NotBlank(message = "Email không được bỏ trống")
    private String email;


    private String avatar;

    @NotBlank(message = "Số điện thoại không được bỏ trống")
    private String phone;

    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private String password;

    private int gender;

    private Boolean isActive;

    private String avatarFile;


}
