package org.armzbot.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;


}
