package org.armzbot.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserProfile implements Serializable {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String provider;
    private String username;

}
