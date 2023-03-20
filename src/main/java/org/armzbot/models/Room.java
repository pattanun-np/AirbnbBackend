package org.armzbot.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.armzbot.models.Audit.DateAudit;

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)



    private String room_id;
    @Column()
    private String host_id;
    @Column()
    private String name;
    @Column()
    private String space;
    @Column()
    private String neighborhood_overview;
    @Column()
    private String access;
    private String[] images;
    private String interaction;

    private String house_rules;
    private String room_type;
    private String property_type;
    private String bed_type;
    private int minimum_nights;
    private int maximum_nights;
    private float price;
    private String monthly_price;
    private float weekly_price;
    private float cleaning_fee;
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
    private int accommodates;

    private String[] amenities;

    private DateAudit created;
}
