package com.grabieckacper.talktalk.service;

import com.grabieckacper.talktalk.model.User;
import com.grabieckacper.talktalk.request.LoginRequest;
import com.grabieckacper.talktalk.response.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthenticationService {
    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(final JwtEncoder jwtEncoder, final AuthenticationManager authenticationManager) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        User user = (User) authentication.getPrincipal();
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("talk-talk")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(authentication.getName())
                .claim("id", user.getId())
                .build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(claimsSet))
                .getTokenValue();

        return new LoginResponse(token);
    }
}
