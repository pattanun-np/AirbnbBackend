package org.armzbot.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity(name = "reservation")
public class Reservation {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 30, nullable = false, updatable = false)
    private String reserve_id;

    @Column(length = 30, nullable = false)
    private String acc_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;


    @Column
    private int guest_amt;

    @Column
    private Date checkIn;

    @Column
    private Date checkOut;

    @Column
    private String payment_status;

    public Reservation(String reserve_id, String acc_id, User user_id,
                       int guest_amt, Date checkIn, Date checkOut,
                       String payment_status) {
        this.reserve_id = reserve_id;
        this.acc_id = acc_id;
        this.user_id = user_id;
        this.guest_amt = guest_amt;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.payment_status = payment_status;
    }

    public String getReserve_id() {
        return reserve_id;
    }

    public void setReserve_id(String reserve_id) {
        this.reserve_id = reserve_id;
    }

    public String getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(String acc_id) {
        this.acc_id = acc_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
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
}
