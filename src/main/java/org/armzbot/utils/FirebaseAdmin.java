package org.armzbot.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.log4j.Log4j2;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class FirebaseAdmin {


    @PostConstruct
    private void onStart() {
            System.out.println("FirebaseAdmin.onStart");
        try {
            initializeFirebaseApp();
        } catch (IOException e) {
            System.out.println("FirebaseAdmin.onStart");
        }

    }

    private void initializeFirebaseApp() throws IOException {

        if (FirebaseApp.getApps() == null || FirebaseApp.getApps().isEmpty()) {
            InputStream serviceAccount = FirebaseAdmin.class.getResourceAsStream("/firebase-service-credentials.json");
            assert serviceAccount != null;
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            FirebaseApp.initializeApp(options);
        }

    }
}
