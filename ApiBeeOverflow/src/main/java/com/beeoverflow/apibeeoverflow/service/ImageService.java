package com.beeoverflow.apibeeoverflow.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class ImageService {

    public String saveAvatar(MultipartFile file) {
        try {
            Path filePath = Paths.get("/images/avatar/");
            //Tạo thư mục nếu chưa tồn tại
            Files.createDirectories(filePath);

            String fileName = String.format("%s.%s", (new Date()).getTime(), file.getContentType().split("/")[1]);

            // Luu file co ten goc vao thu muc static/images
            Files.copy(file.getInputStream(), filePath.resolve(fileName));

            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
