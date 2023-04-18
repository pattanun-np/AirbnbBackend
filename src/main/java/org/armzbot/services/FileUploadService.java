package org.armzbot.services;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {


    private final StorageClient storageClient;

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }

    public String uploadFile(MultipartFile file, String folder_name) throws IOException {
        Bucket bucket = storageClient.bucket();
        String fileName = generateFileName(file.getOriginalFilename());

        String blobString = String.format("assets/%s/images/%s", folder_name, fileName);

        Blob blob = bucket.create(blobString, file.getBytes(), file.getContentType());

        String blob_url = blobString.replace("/","%2F");

        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", bucket.getName(), blob_url);
    }

}
