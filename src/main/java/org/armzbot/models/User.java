package org.armzbot.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity(name = "user")
public class User {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, unique = true, nullable = false, updatable = false)
    private  String id;

    @Column(nullable = false)
    private String username;

    @Column(updatable = false)
    private String firstname;

    @Column(updatable = false)
    private String lastname;
    @Column(nullable = false)
    private String email;

    @Column()
    private String phone;

    @Column()
    private String password;


}
