package org.armzbot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class ReservationRequest {

    private int guest_amt;

    private String acc_id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date checkIn;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date checkOut;

}