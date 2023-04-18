package org.armzbot.controller;

import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.UserAdaptor;
import org.armzbot.dto.UserProfile;
import org.armzbot.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserAdaptor userAdaptor;

    ///api/v1/user/profile
    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getUserProfile() throws UserException {
        UserProfile response = userAdaptor.getUserProfile();
        return ResponseEntity.ok(response);


    }
}
