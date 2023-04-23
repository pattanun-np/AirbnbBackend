package org.armzbot.dto;

import lombok.Data;

@Data
public class AccommodationRequest {

    private String acc_name;

    private String room_type;

    private int max_guests;

    private int minimum_nights;

    private int maximum_nights;

    private float price;

    private String description;

    private String room_address;

    private String room_street;

    private String room_state;

    private String room_country;

    private String room_country_code;

    private String cancellation_policy;

    private double location_lat;

    private double location_long;

    private int bathrooms;

    private int bedroom;

    private boolean has_internet;

    private boolean has_tv;

    private boolean has_kitchen;

    private boolean has_air_conditioning;

    private boolean has_heating;

    private boolean is_active;

}
