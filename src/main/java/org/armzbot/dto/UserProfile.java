package org.armzbot.dto;

import lombok.Data;

@Data
public class UserProfile {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String provider;
    private String username;

}
