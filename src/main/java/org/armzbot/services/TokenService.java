package org.armzbot.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.armzbot.entity.User;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {
    private FirebaseAuth firebaseAuth;

    public TokenService(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;

    }


    public String tokenize(User user) throws FirebaseAuthException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expiresAt = calendar.getTime();


        return firebaseAuth.createCustomToken(user.getId(), null);
    }

    public FirebaseToken verify(String token) {
        try {
            return FirebaseAuth.getInstance().verifyIdToken(token);

        } catch (Exception e) {
            return null;
        }
    }

}
