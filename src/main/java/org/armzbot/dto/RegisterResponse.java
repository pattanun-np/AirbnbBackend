package org.armzbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterResponse {
    @JsonProperty("message")
    private String message;


}
