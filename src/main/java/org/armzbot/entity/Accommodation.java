package org.armzbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

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


    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @BatchSize(size = 1000)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "accommodation", orphanRemoval = false, cascade = CascadeType.ALL, fetch = EAGER)
    @BatchSize(size = 1000)
    @JsonIgnore
    private List<AccommodationImages> accommodationImages;

    public List<AccommodationImages> getAccommodationImages() {
        return accommodationImages;
    }

    public void setAccommodationImages(List<AccommodationImages> accommodationImages) {
        this.accommodationImages = accommodationImages;
    }


    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
