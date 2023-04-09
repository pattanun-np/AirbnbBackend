package org.armzbot.controller;

import org.armzbot.models.User;
import org.armzbot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices){
        this.userServices = userServices;
    }

    @GetMapping
    public ResponseEntity<Optional<User>> getAllUsers() {
        Optional<User> response = userServices.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{user_id}")
    public ResponseEntity<User> getHostById(@PathVariable(value = "user_id") String id) throws IOException {
        User response = userServices.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> addHost(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String firstname,
                        @RequestParam String lastname,
                        @RequestParam String email,
                        @RequestParam String phone) throws IOException {
        userServices.addUser(username,password, firstname, lastname, email, phone);
        return ResponseEntity.ok("User has been added successfully.");
    }

    @PutMapping(path = "/{User_id}")
    public ResponseEntity<String> updateHost(@PathVariable(value = "user_id") String id,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String firstname,
                           @RequestParam(required = false) String lastname,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String phone) throws IOException {
       userServices.updateUserById(id, username, firstname, lastname, email, phone);
       return ResponseEntity.ok("update successful");
    }

    //soft delete
    @PutMapping(path = "/{user_id}")
    public ResponseEntity<String> deleteHost(@PathVariable(value = "user_id") String id) throws IOException {
        userServices.deleteUserById(id);
        return ResponseEntity.ok("User has been deleted.");
    }

}
