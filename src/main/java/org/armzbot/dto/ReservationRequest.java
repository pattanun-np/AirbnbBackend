package org.armzbot.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ReservationRequest {



    private Date CheckIn;
    private Date CheckOut;


}
