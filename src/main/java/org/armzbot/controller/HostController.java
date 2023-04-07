package org.armzbot.controller;

import org.armzbot.models.Host;
import org.armzbot.services.HostServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Host> getAllHost() {
        return hostServices.getAllHost();
    }

    @GetMapping(path = "/{id}")
    public Host getHostById(@PathVariable("id") String id) throws IOException {
        return hostServices.getHostById(id);
    }


    @PostMapping
    public void addHost(@RequestParam String id,
                        @RequestParam String username,
                        @RequestParam String firstname,
                        @RequestParam String lastname,
                        @RequestParam String email,
                        @RequestParam String phone) throws IOException {
        hostServices.addHost(new Host(id, username, firstname, lastname, email, phone));
    }

    @PutMapping(path = "/{id}")
    public void updateHost(@PathVariable("id") String id,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String firstname,
                           @RequestParam(required = false) String lastname,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String phone) throws IOException {
       hostServices.updateHostById(id, username, firstname, lastname, email, phone);
    }

    //soft delete
    @PutMapping(path = "/{id}")
    public void deleteHost(@PathVariable("id") String id) throws IOException {
        hostServices.deleteHostById(id);
    }

}
