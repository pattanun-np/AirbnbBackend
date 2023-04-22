package org.armzbot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity(name = "accommodations")
@AllArgsConstructor
@NoArgsConstructor
public class Accommodation extends BaseEntity implements Serializable {

    @Column()
    private String acc_name;
    @Column()
    private String room_type;
    @Column
    private int max_guest;
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

    @Column()
    private boolean is_active;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "accommodation", orphanRemoval = true)
    private List<AccommodationImages> accommodationImages;


    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
