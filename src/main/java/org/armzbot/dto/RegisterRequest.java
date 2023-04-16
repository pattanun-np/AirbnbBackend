package org.armzbot.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String phone;


}
