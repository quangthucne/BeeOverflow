package com.beeoverflow.apibeeoverflow.beans;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentBean {

    private int id;
    
    @NotBlank(message = "Không được bỏ trống tên")
    private String name;

    @NotBlank(message = "Mô tả không được bỏ trống")
    private String description;

    @NotBlank(message = "Tài liệu rỗng")
    private String docUrl;

    private int price;

    private boolean isFree;
}
