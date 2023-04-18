package org.armzbot.controller;

import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.AccommodationAdaptor;
import org.armzbot.domain.common.query.SearchRequest;
import org.armzbot.dto.*;
import org.armzbot.entity.Accommodation;
import org.armzbot.exception.BaseException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accommodation")
@RequiredArgsConstructor
public class AccommodationController {
    private final AccommodationAdaptor accommodationAdaptor;


    ///api/v1/accommodation/{accommodation_id}
    @GetMapping(path = "/{accommodation_id}")
    public ResponseEntity<AccommodationObject> getAccommodationById(
            @PathVariable(value = "accommodation_id")
            String accommodation_id) throws Exception {
        AccommodationObject res = accommodationAdaptor.getAccommodationById(accommodation_id);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<AccommodationObject>> getAllAccommodations() {
        List<AccommodationObject> res = accommodationAdaptor.getAllAccommodations();
        return ResponseEntity.ok(res);
    }

    @PostMapping (path = "/search")
    public Page<AccommodationObject> search(@RequestBody SearchRequest request) throws BaseException {
        return accommodationAdaptor.searchAccommodation(request);
    }

    ///api/v1/accommodation

    @PostMapping(path = "")
    public ResponseEntity<AccommodationResponse> addAccommodation(
            @RequestBody AccommodationRequest r) {

        AccommodationResponse response = accommodationAdaptor.addAccommodation(r);
        response.setMessage("Accommodation created successfully");
        return ResponseEntity.ok(response);

    }

    @DeleteMapping(path = "/{accommodation_id}")
    public ResponseEntity<AccommodationResponse> deleteAccommodation(
            @PathVariable(value = "accommodation_id") String accommodation_id) throws Exception {
        AccommodationResponse response = accommodationAdaptor.deleteAccommodationById(accommodation_id);
        response.setMessage("Accommodation deleted successfully");
        return ResponseEntity.ok(response);

    }

    ///api/v1/accommodation/{accommodation_id}
    @PutMapping(path = "/{accommodation_id}")
    public ResponseEntity<AccommodationResponse> updateAccommodation(
            @PathVariable(value = "accommodation_id") String accommodation_id,
            @RequestBody Accommodation accommodation) throws Exception {
        AccommodationResponse response = accommodationAdaptor
                .updateAccommodation(accommodation_id, accommodation);
        response.setMessage("Accommodation updated successfully");
        return ResponseEntity.ok(response);

    }

    ///api/v1/accommodation/{accommodation_id}/image
    @PostMapping(path = "/{accommodation_id}/image")
    public ResponseEntity<UploadImageToAccommodationResponse> addImage(
            @PathVariable(value = "accommodation_id") String accommodation_id,
            @RequestParam("file") MultipartFile file) throws Exception {

        ImageUpload imageUpload = new ImageUpload();
        imageUpload.setImage_name(file.getOriginalFilename());
        imageUpload.setImageSource(file);
        imageUpload.setFileSize(file.getSize());

        System.out.println("Image name: " + imageUpload.getImage_name());
        System.out.println("Image source: " + imageUpload.getImageSource());
        System.out.println("Image size: " + imageUpload.getFileSize());

        UploadImageToAccommodationResponse response = accommodationAdaptor
                .uploadImageToAccommodation(imageUpload, accommodation_id);
        response.setMessage("Accommodation image updated successfully");
        return ResponseEntity.ok(response);

    }


}

