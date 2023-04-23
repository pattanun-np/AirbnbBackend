package org.armzbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponse implements Serializable {

    @JsonProperty("message")
    private String message;

    @JsonProperty("token")
    private String token;

}
