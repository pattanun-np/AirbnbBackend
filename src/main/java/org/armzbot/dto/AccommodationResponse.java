package org.armzbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccommodationResponse {
    @JsonProperty("message")
    private String message;
}
