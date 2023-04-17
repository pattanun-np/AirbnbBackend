package org.armzbot.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ReservationRequest {
    private String user_id;
    private String reserve_id;
    private String reserve_date;
    private String reserve_time;
    private String reserve_status;
    private String reserve_type;
    private String reserve_price;
    private String reserve_quantity;
    private String reserve_total;


    private Date CheckIn;
    private Date CheckOut;


}
