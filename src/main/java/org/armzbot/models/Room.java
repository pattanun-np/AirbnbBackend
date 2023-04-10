package org.armzbot.models;

import jakarta.persistence.*;
import org.armzbot.models.Audit.DateAudit;
import org.hibernate.annotations.GenericGenerator;


@Entity(name = "room")
public class Room  {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, unique = true, nullable = false, updatable = false)
    private  String id;

    @Column(length = 36)
    private String host_id;
    @Column()
    private String name;
    @Column()
    private String space;
    @Column()
    private String neighborhood_overview;
    @Column()
    private String access;
    @Column()
    private String images;
    @Column()
    private String interaction;
    @Column()
    private String house_rules;
    @Column()
    private String room_type;
    @Column()
    private String property_type;
    @Column()
    private String bed_type;
    @Column()
    private int minimum_nights;
    @Column()
    private int maximum_nights;
    @Column()
    private float price;
    @Column()
    private String monthly_price;
    @Column()
    private float weekly_price;
    @Column()
    private float cleaning_fee;
    @Column()
    private String description;
    @Column()
    private String room_address;
    @Column()
    private String room_street;
    @Column()
    private String room_state;
    @Column()
    private String room_country;
    @Column()
    private String room_country_code;
    @Column()
    private String cancellation_policy;
    @Column()
    private double location_lat;
    @Column()
    private double location_long;
    @Column()
    private int bathrooms;
    @Column()
    private int bedroom;
    @Column()
    private int accommodates;
    @Column()

    private String amenities;
    @Column()
    private DateAudit created;
}
