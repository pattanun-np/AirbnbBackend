package org.armzbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "accommodations")
public class Accommodation extends BaseEntity implements Serializable {
    @Column()
    private String acc_name;
    @Column()
    private String room_type;
    @Column()
    private int minimum_nights;
    @Column()
    private int maximum_nights;
    @Column()
    private float price;

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
    private boolean has_internet;

    @Column()
    private boolean has_tv;

    @Column()
    private boolean has_kitchen;

    @Column()
    private boolean has_air_conditioning;


    @Column()
    private boolean has_heating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;
    @OneToMany(mappedBy = "accommodation", orphanRemoval = true)
    private List<AccommodationImages> accommodationImages;
}
