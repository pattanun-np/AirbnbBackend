package org.armzbot.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "airbnb.host")
public class Host {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 30, nullable = false, updatable = false)
    private String host_id;

    @Column(length = 40, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String firstname;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column
    private boolean is_Active;
    public Host(String host_id, String username,
                String firstname, String lastname,
                String email, String phone) {
        this.host_id = host_id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.is_Active = true;
    }

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return is_Active;
    }

    public void setActive(boolean is_Active) {
        this.is_Active = is_Active;
    }

    public boolean hasNull() {
        return host_id == null &&
                username == null &&
                firstname == null &&
                lastname == null &&
                email == null &&
                phone == null;
    }
}