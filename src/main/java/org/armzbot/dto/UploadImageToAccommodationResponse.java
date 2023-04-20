package org.armzbot.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadImageToAccommodationResponse implements Serializable {
    private String message;

    private String ImageUrl;
}
