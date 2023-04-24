package org.armzbot.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.StorageClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Configuration
@Log4j2
public class FirebaseAdmin {

    @Value("${env.app_services_account_key}")
    String serviceAccountKey;

    private byte[] DecodeServiceAccountKey(String serviceAccountKey) {
//        System.out.println("serviceAccountKey: " + serviceAccountKey);
        return Base64.getDecoder().decode(serviceAccountKey);
    }

    private void initializeFirebaseApp() throws IOException {

        if (FirebaseApp.getApps() == null || FirebaseApp.getApps().isEmpty()) {
            InputStream serviceAccount = new java.io.ByteArrayInputStream(DecodeServiceAccountKey(serviceAccountKey));
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setProjectId("airbnbprojects-79e2e")
                    .setStorageBucket("airbnbprojects-79e2e.appspot.com")
                    .setDatabaseUrl("https://airbnbprojects-79e2e.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        }

    }

    @Bean
    public FirebaseAuth firebaseAuth() {

        return FirebaseAuth.getInstance();
    }

    @Bean
    public StorageClient storageClient() {
        return StorageClient.getInstance();
    }

    @PostConstruct
    private void onStart() {
        log.info("FirebaseAdmin.onStart");
        try {
            initializeFirebaseApp();
        } catch (IOException e) {
            log.error("FirebaseAdmin Error onStart", e);
        }

    }
}