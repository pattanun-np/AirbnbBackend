package org.armzbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.dto.AccommodationObject;
import org.armzbot.entity.Accommodation;
import org.armzbot.exception.AccommodationException;
import org.armzbot.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    private AccommodationObject BuildAccommodationObj(Accommodation acc) {
        AccommodationObject acc_obj = new AccommodationObject();
        acc_obj.setAcc_name(acc.getAcc_name());
        acc_obj.setMax_guest(acc.getMax_guest());
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
        return BuildAccommodationObj(acc);
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
        return acc;
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


}

