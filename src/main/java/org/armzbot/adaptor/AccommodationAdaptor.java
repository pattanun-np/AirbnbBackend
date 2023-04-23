package org.armzbot.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.dto.*;
import org.armzbot.entity.Accommodation;
import org.armzbot.entity.User;
import org.armzbot.exception.AccommodationException;
import org.armzbot.exception.BaseException;
import org.armzbot.exception.UserException;
import org.armzbot.services.AccommodationService;
import org.armzbot.services.UserService;
import org.armzbot.utils.SecurityUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AccommodationAdaptor {


    private final AccommodationService accommodationService;
    private final UserService userService;


    public AccommodationResponse addAccommodation(AccommodationRequest acc) {
        Optional<String> opt = SecurityUtil.getCurrentUserId();

        if (opt.isEmpty()) {
            try {
                throw UserException.unauthorized();
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
        }
        String userId = opt.get();
        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            try {
                throw UserException.notFound();
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
        }
        User user = optUser.get();
        Accommodation accommodation = Accommodation.builder()
                .acc_name(acc.getAcc_name())
                .bedroom(acc.getBedroom())
                .bathrooms(acc.getBathrooms())
                .price(acc.getPrice())
                .description(acc.getDescription())
                .room_address(acc.getRoom_address())
                .room_street(acc.getRoom_street())
                .room_state(acc.getRoom_state())
                .room_country(acc.getRoom_country())
                .room_country_code(acc.getRoom_country_code())
                .cancellation_policy(acc.getCancellation_policy())
                .location_lat(acc.getLocation_lat())
                .location_long(acc.getLocation_long())
                .has_internet(acc.isHas_internet())
                .has_tv(acc.isHas_tv())
                .has_kitchen(acc.isHas_kitchen())
                .has_air_conditioning(acc.isHas_air_conditioning())
                .has_heating(acc.isHas_heating())
                .minimum_nights(acc.getMinimum_nights())
                .maximum_nights(acc.getMaximum_nights())
                .room_type(acc.getRoom_type())
                .is_active(true)
                .user(user)
                .build();

        accommodationService.addAccommodation(accommodation);

        return new AccommodationResponse();
    }

    public AccommodationResponse updateAccommodation(String acc_id, Accommodation accommodation) throws BaseException {
        accommodationService.updateAccommodation(acc_id, accommodation);
        return new AccommodationResponse();
    }


    public AccommodationResponse deleteAccommodationById(String acc_id) throws BaseException {
        accommodationService.deleteAccommodationById(acc_id);
        return new AccommodationResponse();
    }

    public AccommodationObject getAccommodationById(String acc_id) throws BaseException {

        return accommodationService.getAccommodationById(acc_id);


    }

    public List<AccommodationObject> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    public UploadImageToAccommodationResponse uploadImageToAccommodation(ImageUpload request, String acc_id) throws IOException, AccommodationException {
        String image_url = accommodationService.UploadImageToAccommodation(acc_id, request.getImageSource());

        UploadImageToAccommodationResponse res = new UploadImageToAccommodationResponse();
        res.setImageUrl(image_url);
        return res;
    }


    public List<AccommodationObject> getAccommodationsByUserId() throws AccommodationException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();

        if (opt.isEmpty()) {
            try {
                throw UserException.unauthorized();
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
        }
        String userId = opt.get();
        return accommodationService.getAccommodationsByUserId(userId);
    }
}
