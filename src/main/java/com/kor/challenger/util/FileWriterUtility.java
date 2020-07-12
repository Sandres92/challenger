package com.kor.challenger.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileWriterUtility {
    @Value("${upload.path}")
    private String uploadPath;

    public String saveFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists() && !file.getOriginalFilename().isEmpty()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();

            System.out.println(file.getSize());
            System.out.println(file.getContentType());

            int index = file.getOriginalFilename().lastIndexOf(".");
            String ext = file.getOriginalFilename().substring(index);
            String name = file.getOriginalFilename();

            if(".dat".equals(ext)){
                name = file.getOriginalFilename().substring(0,index)+".jpg";
            }

            String resultFilename = (uuidFile + "." + name).replace("\\s", "");
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            return resultFilename;
        }

        return null;
    }
}
