package com.beeoverflow.apibeeoverflow.service;

import com.beeoverflow.apibeeoverflow.dto.QuestionDTO;
import com.beeoverflow.apibeeoverflow.entities.Answer;
import com.beeoverflow.apibeeoverflow.entities.ImagesAn;
import com.beeoverflow.apibeeoverflow.entities.ImagesQue;
import com.beeoverflow.apibeeoverflow.entities.Question;
import com.beeoverflow.apibeeoverflow.jpas.ImagesAnsJPA;
import com.beeoverflow.apibeeoverflow.jpas.ImagesQuesJPA;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
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
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    ImagesQuesJPA imagesQuesJPA;

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    ImagesAnsJPA imagesAnsJPA;

    public String saveAvatar(MultipartFile file) {
        try {
            String url = saveImage(file);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveImages (List<MultipartFile> files, Question question) {
        try {
            for (MultipartFile file : files) {
                ImagesQue imagesQue = new ImagesQue();
                imagesQue.setQues(question);

                String url = saveImage(file);

                System.out.println(url);

                imagesQue.setName(url);
                imagesQuesJPA.save(imagesQue);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveImagesAns (List<MultipartFile> files, Answer answer) {
        try {
            for (MultipartFile file : files) {
                ImagesAn imagesAn = new ImagesAn();
                imagesAn.setAns(answer);

                String url = saveImage(file);

                System.out.println(url);

                imagesAn.setName(url);
                imagesAnsJPA.save(imagesAn);

            }
        } catch (Exception e) {
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

    public String saveImage(MultipartFile file) {
        try {
            Map<String, Object> uploadParams = Map.of(
                    "folder", "images",
                    "transformation", new Transformation()
                            .fetchFormat("auto")     // auto chọn định dạng tối ưu
                            .quality("auto:eco")     // nén nhưng vẫn giữ chất lượng khá tốt
            );

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

            return (String) uploadResult.get("secure_url");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
