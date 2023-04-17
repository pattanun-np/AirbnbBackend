package org.armzbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReservationResponse {
    @JsonProperty("message")
    private String message;

}
