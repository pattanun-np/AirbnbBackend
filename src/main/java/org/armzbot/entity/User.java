package org.armzbot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "users")
public class User extends BaseEntity implements Serializable {

    @Column(nullable = false, length = 40, columnDefinition = "varchar(40)", unique = true)
    private String username;

    @Column(updatable = false, length = 50, columnDefinition = "varchar(50)")
    private String firstname;

    @Column(updatable = false, length = 50, columnDefinition = "varchar(50)")
    private String lastname;
    @Column(nullable = false, length = 40, columnDefinition = "varchar(40)")
    private String email;

    @Column(length = 20, columnDefinition = "varchar(20)")
    private String phone;

    @Column(length = 100, columnDefinition = "varchar(100)")
    private String password;

    @Column(length = 10, columnDefinition = "varchar(10) default 'local'")
    private String provider;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Accommodation> accommodations;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
