package org.armzbot.controller;

import org.armzbot.models.User;
import org.armzbot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/host")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices){
        this.userServices = userServices;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllHosts() {
        List<User> response = userServices.getAllHosts();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getHostById(@PathVariable("id") String id) throws IOException {
        User response = userServices.getHostById(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public void addHost(@RequestParam String id,
                        @RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String firstname,
                        @RequestParam String lastname,
                        @RequestParam String email,
                        @RequestParam String phone) throws IOException {
        userServices.addHost(new User(id, username,password, firstname, lastname, email, phone));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateHost(@PathVariable("id") String id,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String firstname,
                           @RequestParam(required = false) String lastname,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String phone) throws IOException {
       userServices.updateHostById(id, username, firstname, lastname, email, phone);
       return ResponseEntity.ok("update successful");
    }

    //soft delete
    @PutMapping(path = "/{id}")
    public void deleteHost(@PathVariable("id") String id) throws IOException {
        userServices.deleteHostById(id);
    }

}
