package org.armzbot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
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
    @ColumnDefault("false")
    private boolean isDeleted;

    @Column(length = 100, columnDefinition = "varchar(100)")
    private String password;

    @Column(length = 10, columnDefinition = "varchar(10) default 'local'")
    private String provider;



    @OneToMany(mappedBy = "user",fetch = EAGER)
    private List<Accommodation> accommodation;

    public Collection<Accommodation> getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(List<Accommodation> accommodation) {
        this.accommodation =  accommodation;
    }
}
