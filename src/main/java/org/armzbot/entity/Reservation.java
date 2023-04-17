package org.armzbot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseEntity implements Serializable {
    @Column(length = 3, nullable = false)
    private int guest_ami;

    @Column(length = 30, nullable = false)
    private Date checkIn;

    @Column(length = 30, nullable = false)
    private Date checkOut;

    @Column(length = 30, nullable = false)
    private String payment_status;

    @Column(length = 30, nullable = false)
    private boolean is_active;

    @Column(length = 30, nullable = false)
    private float price;

    @Column(length = 30, nullable = false)
    private float total_price;


    public void setIsActive(boolean is_active) {
        this.is_active = is_active;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_id", nullable = false, updatable = false)
    private Accommodation accommodation;
}
