package com.beeoverflow.apibeeoverflow.service;

import com.beeoverflow.apibeeoverflow.entities.ImagesQue;
import com.beeoverflow.apibeeoverflow.entities.Question;
import com.beeoverflow.apibeeoverflow.jpas.ImagesQuesJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    ImagesQuesJPA imagesQuesJPA;

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

    public void saveImagesQues (List<MultipartFile> files, Question question) {


        try {
            Path filePath = Paths.get("images/questions/");

            Files.createDirectories(filePath);

            for (MultipartFile file : files) {
                ImagesQue imagesQue = new ImagesQue();
                imagesQue.setQues(question);

                String fileName = String.format("%s.%s", (new Date()).getTime(), "jpg");
                Files.copy(file.getInputStream(), filePath.resolve(fileName));
                System.out.println(fileName);

                imagesQue.setName(fileName);
                imagesQuesJPA.save(imagesQue);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteImage(String imageName) {
        String path = "images/questions/" + imageName;
        File file = new File(path);
        if (file.exists()) {
            return file.delete(); // Trả về true nếu xóa thành công
        } else {
            System.out.println("File không tồn tại: " + imageName);
            return false;
        }
    }

    public void deleteImagesQues(List<ImagesQue> imagesQues) {
        for (ImagesQue imagesQue : imagesQues) {
            deleteImage(imagesQue.getName());
            imagesQuesJPA.delete(imagesQue);
        }
    }

}
