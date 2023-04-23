package org.armzbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.dto.AccommodationObject;
import org.armzbot.entity.Accommodation;
import org.armzbot.entity.AccommodationImages;
import org.armzbot.exception.AccommodationException;
import org.armzbot.repository.AccommodationImageRepository;
import org.armzbot.repository.AccommodationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationImageRepository accommodationImageRepository;

    private final FileUploadService fileUploadService;

    public AccommodationObject BuildAccommodationObj(Accommodation acc) {
        AccommodationObject acc_obj = new AccommodationObject();
        acc_obj.setAcc_name(acc.getAcc_name());
        acc_obj.setMax_guests(acc.getMax_guests());
        acc_obj.setBedroom(acc.getBedroom());
        acc_obj.setBathrooms(acc.getBathrooms());
        acc_obj.setPrice(acc.getPrice());
        acc_obj.setDescription(acc.getDescription());
        acc_obj.setRoom_address(acc.getRoom_address());
        acc_obj.setRoom_street(acc.getRoom_street());
        acc_obj.setRoom_state(acc.getRoom_state());
        acc_obj.setRoom_country(acc.getRoom_country());
        acc_obj.setRoom_country_code(acc.getRoom_country_code());
        acc_obj.setCancellation_policy(acc.getCancellation_policy());
        acc_obj.setLocation_lat(acc.getLocation_lat());
        acc_obj.setLocation_long(acc.getLocation_long());
        acc_obj.setHas_internet(acc.isHas_internet());
        acc_obj.setHas_tv(acc.isHas_tv());
        acc_obj.setHas_kitchen(acc.isHas_kitchen());
        acc_obj.setHas_air_conditioning(acc.isHas_air_conditioning());
        acc_obj.setHas_heating(acc.isHas_heating());
        acc_obj.setMinimum_nights(acc.getMinimum_nights());
        acc_obj.setMaximum_nights(acc.getMaximum_nights());
        acc_obj.setRoom_type(acc.getRoom_type());
        acc_obj.set_active(acc.is_active());
        acc_obj.setUser_id(acc.getUser().getId());
        acc_obj.setAccommodation_id(acc.getId());
        acc_obj.setAccommodationImages(acc.getAccommodationImages());

        return acc_obj;
    }

    public List<AccommodationObject> getAllAccommodations() {
        ArrayList<AccommodationObject> accommodations = new ArrayList<>();
        int limit = 10;

        List<Accommodation> result = accommodationRepository.findAll();

        for (Accommodation acc : result) {
//            System.out.println(result.get(i).getAcc_name());
            if (acc.is_active()) {
                System.out.println(acc.getId());

                accommodations.add(BuildAccommodationObj(acc));
            }
        }

        return accommodations;

    }


    public AccommodationObject getAccommodationById(String acc_id) throws AccommodationException {
        System.out.println(acc_id);

        Optional<Accommodation> res = accommodationRepository.findByID(acc_id);

        if (res.isEmpty()) {
            throw AccommodationException.notFround();
        }
        System.out.println(res.get().getAcc_name());
        if (!res.get().is_active()) {
            throw AccommodationException.notFround();
        }
        Accommodation acc = res.get();
        return BuildAccommodationObj(acc);
    }

    public void addAccommodation(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    public void updateAccommodation(String acc_id, Accommodation accommodation) {
        Optional<Accommodation> repository = accommodationRepository.findByID(acc_id);
        if (repository.isEmpty()) {
            return;
        }
        Accommodation result = repository.get();
        result.setAcc_name(accommodation.getAcc_name());
        result.setBedroom(accommodation.getBedroom());
        result.setBathrooms(accommodation.getBathrooms());
        result.setPrice(accommodation.getPrice());
        result.setDescription(accommodation.getDescription());
        result.setRoom_address(accommodation.getRoom_address());
        result.setRoom_street(accommodation.getRoom_street());
        result.setRoom_state(accommodation.getRoom_state());
        result.setRoom_country(accommodation.getRoom_country());
        result.setRoom_country_code(accommodation.getRoom_country_code());
        result.setCancellation_policy(accommodation.getCancellation_policy());
        result.setLocation_lat(accommodation.getLocation_lat());
        result.setLocation_long(accommodation.getLocation_long());
        result.setHas_internet(accommodation.isHas_internet());
        result.setHas_tv(accommodation.isHas_tv());
        result.setHas_kitchen(accommodation.isHas_kitchen());
        result.setHas_air_conditioning(accommodation.isHas_air_conditioning());
        result.setHas_heating(accommodation.isHas_heating());
        result.setMinimum_nights(accommodation.getMinimum_nights());
        result.setMaximum_nights(accommodation.getMaximum_nights());
        result.setRoom_type(accommodation.getRoom_type());
        result.set_active(accommodation.is_active());
        result.setMax_guests(accommodation.getMax_guests());
        result.setUser(accommodation.getUser());
        accommodationRepository.save(result);
    }

    public void deleteAccommodationById(String acc_id) {

        Optional<Accommodation> repository = accommodationRepository.findByID(acc_id);
        if (repository.isEmpty()) {
            return;
        }

        Accommodation result = repository.get();
        result.set_active(false);
        accommodationRepository.save(result);
    }

    public String UploadImageToAccommodation(String acc_id, MultipartFile file) throws IOException, AccommodationException {
        Optional<Accommodation> repository = accommodationRepository.findByID(acc_id);
        String image_url = fileUploadService.uploadFile(file, "accommodation_images");
        if (repository.isEmpty()) {
            throw AccommodationException.notFround();
        }
        Accommodation result = repository.get();
        AccommodationImages acc_image = AccommodationImages.builder().accommodation(result).url(image_url).build();

        AccommodationImages accommodationImg = accommodationImageRepository.save(acc_image);


        ArrayList<AccommodationImages> images = new ArrayList<>();
        images.add(accommodationImg);

        result.setAccommodationImages(images);


        accommodationRepository.save(result);

        return image_url;


    }

    public Accommodation getAccommodationEntityById(String acc_id) throws AccommodationException {
        //System.out.println(acc_id);

        Optional<Accommodation> res = accommodationRepository.findByID(acc_id);

        if (res.isEmpty()) {
            throw AccommodationException.notFround();
        }
        //System.out.println(res.get().getAcc_name());
        if (!res.get().is_active()) {
            throw AccommodationException.notFround();
        }

        Accommodation acc = res.get();
        acc.setAccommodationImages(accommodationImageRepository.findByAccommodationId(acc.getId()));
        return acc;

    }

    public List<AccommodationObject> getAccommodationsByUserId(String user_id) throws AccommodationException {
        ArrayList<AccommodationObject> accommodations = new ArrayList<>();
        int limit = 10;

        List<Accommodation> result = accommodationRepository.findByUserId(user_id);
        System.out.println(result.size());
        if (result.isEmpty()) {

            return accommodations;
        }

        for (Accommodation acc : result) {
//            System.out.println(result.get(i).getAcc_name());
            acc.setAccommodationImages(accommodationImageRepository.findByAccommodationId(acc.getId()));

            accommodations.add(BuildAccommodationObj(acc));

        }
        return accommodations;
    }
}

