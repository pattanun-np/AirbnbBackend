package org.armzbot.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class AuthService {

    public String getCustomToken(String uid, String provider) throws FirebaseAuthException {

        Map<String, Object> additionalClaims = new HashMap<String, Object>();
        additionalClaims.put("provider", provider);
        return FirebaseAuth.getInstance()
                .createCustomToken(uid, additionalClaims);
    }


    // Verify the ID token while checking if the token is revoked by passing checkRevoked
    public void verifyIDToken(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }

    }


}
