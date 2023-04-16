package org.armzbot.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@Log4j2
public class FirebaseAdmin {


    private void initializeFirebaseApp() throws IOException {

        if (FirebaseApp.getApps() == null || FirebaseApp.getApps().isEmpty()) {
            InputStream serviceAccount = FirebaseAdmin.class.getResourceAsStream("/serviceAccount.json");
            assert serviceAccount != null;
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            FirebaseApp.initializeApp(options);
        }

    }

    @Bean
    public FirebaseAuth firebaseAuth(){

        return FirebaseAuth.getInstance();
    }

    @PostConstruct
    private void onStart() {
        System.out.println("FirebaseAdmin.onStart");
        try {
            initializeFirebaseApp();
        } catch (IOException e) {
            System.out.println("FirebaseAdmin.onStart");
        }

    }
}
