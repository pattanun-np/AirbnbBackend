package org.armzbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity(name = "accommodation_images")
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationImages extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String url;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id", nullable = false, updatable = false)
    @JsonIgnore
    private Accommodation accommodation;

}
