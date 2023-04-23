package org.armzbot.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
public class ReservationObject implements Serializable {

    private String reserve_id;

    private int guest_amt;

    private Date checkIn;

    private Date checkOut;

    private String payment_status;

    private boolean is_active;

    private float price;

    private float total_price;

    private String user_id;


    private int total_nights;
    private AccommodationObject accommodation;
}