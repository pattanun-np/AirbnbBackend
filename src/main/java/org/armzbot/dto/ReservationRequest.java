package org.armzbot.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class ReservationRequest implements Serializable {


    private Date CheckIn;
    private Date CheckOut;


}
