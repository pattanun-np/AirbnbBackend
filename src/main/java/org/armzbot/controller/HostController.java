package org.armzbot.controller;

import org.armzbot.models.User;
import org.armzbot.services.HostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/host")
public class HostController {

    private final HostServices hostServices;

    @Autowired
    public HostController(HostServices hostServices){
        this.hostServices = hostServices;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllHosts() {
        List<User> response = hostServices.getAllHosts();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getHostById(@PathVariable("id") String id) throws IOException {
        User response = hostServices.getHostById(id);
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
        hostServices.addHost(new User(id, username,password, firstname, lastname, email, phone));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateHost(@PathVariable("id") String id,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String firstname,
                           @RequestParam(required = false) String lastname,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String phone) throws IOException {
       hostServices.updateHostById(id, username, firstname, lastname, email, phone);
       return ResponseEntity.ok("update successful");
    }

    //soft delete
    @PutMapping(path = "/{id}")
    public void deleteHost(@PathVariable("id") String id) throws IOException {
        hostServices.deleteHostById(id);
    }

}
