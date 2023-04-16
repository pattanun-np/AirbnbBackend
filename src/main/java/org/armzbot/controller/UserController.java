package org.armzbot.controller;

import org.armzbot.adaptor.UserAdaptor;
import org.armzbot.dto.UserProfile;
import org.armzbot.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserAdaptor userAdaptor;

    @Autowired
    public UserController(UserAdaptor userAdaptor) {

        this.userAdaptor = userAdaptor;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getUserProfile() throws UserException {
        UserProfile response = userAdaptor.getUserProfile();
        return null;

    }
}
