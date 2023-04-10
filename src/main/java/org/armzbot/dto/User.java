package org.armzbot.dto;

public class User {


    private String id;
    private String username;
    private String firstname;
    private String lastname;

    private String email;

    private String password;

    public User() {
    }

    public User(
            String id,
            String username,
            String firstname,
            String lastname,
            String email,
            String password) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public  String getPassword(){
        return  password;
    }


}
