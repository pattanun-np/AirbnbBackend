package org.armzbot.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class ImageUpload implements Serializable {
    private String image_name;
    private MultipartFile imageSource;

    private float FileSize;


}
