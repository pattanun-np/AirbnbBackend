package org.armzbot.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TokenService {
    private final FirebaseAuth firebaseAuth;


    public String tokenize(User user) throws FirebaseAuthException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authorities = auth.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));


        return firebaseAuth.createCustomToken(user.getId(),
                Collections.singletonMap("authorities", authorities));
    }

    public FirebaseToken verify(String token) throws FirebaseAuthException {
        return firebaseAuth.verifyIdToken(token, true);

    }

    public void revoke(String token) throws FirebaseAuthException {
        firebaseAuth.revokeRefreshTokens(token);
    }

    public void setCustomClaims(String token, String claims) throws FirebaseAuthException {
        firebaseAuth.setCustomUserClaims(token, Collections.singletonMap("authorities", claims));
    }

}
