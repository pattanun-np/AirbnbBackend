package org.armzbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "reservations")
public class Reservation extends BaseEntity implements Serializable {
    @Column(length = 3, nullable = false)
    private int guest_amt;

    @Column(length = 30, nullable = false)
    private Date checkIn;

    @Column(length = 30, nullable = false)
    private Date checkOut;

    @Column(length = 30, nullable = false)
    private String payment_status;

    @Column(length = 30, nullable = false)
    private String booking_id;
    @Column(length = 30, nullable = false)
    private boolean is_active;

    @Column(length = 30, nullable = false)
    private float price;

    @Column(length = 30, nullable = false)
    private float total_price;

    public String getBooking_id() {
        return booking_id;
    }

    public void setBook_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public int getGuest_amt() {
        return guest_amt;
    }

    public void setGuest_amt(int guest_amt) {
        this.guest_amt = guest_amt;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_id", nullable = false, updatable = false)
    private Accommodation accommodation;
}
