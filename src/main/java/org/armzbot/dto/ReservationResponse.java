package org.armzbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReservationResponse implements Serializable {
    @JsonProperty("message")
    private String message;

}
