package org.armzbot.controller;

import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.AccommodationAdaptor;
import org.armzbot.adaptor.UserAdaptor;
import org.armzbot.dto.AccommodationObject;
import org.armzbot.dto.UserProfile;
import org.armzbot.exception.BaseException;
import org.armzbot.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserAdaptor userAdaptor;
    private final AccommodationAdaptor accommodationAdaptor;

    ///api/v1/user/profile
    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getUserProfile() throws UserException {
        UserProfile response = userAdaptor.getUserProfile();
        return ResponseEntity.ok(response);

    }


    ///api/v1/user/profile/{user_id}
    @GetMapping("/profile/{user_id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable(value = "user_id") String user_id) throws UserException {
        UserProfile response = userAdaptor.getProfile(user_id);
        return ResponseEntity.ok(response);
    }

    ///api/v1/user/my-accommodation
    @GetMapping("/my-accommodation")
    public ResponseEntity<List<AccommodationObject>> getMyAccommodation() throws BaseException {
        List<AccommodationObject> response = accommodationAdaptor.getMyAccommodations();
        return ResponseEntity.ok(response);
    }
}
