package com.beeoverflow.apibeeoverflow.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageBean {

    private MultipartFile avatarFile;

    private List<MultipartFile> images;


}
