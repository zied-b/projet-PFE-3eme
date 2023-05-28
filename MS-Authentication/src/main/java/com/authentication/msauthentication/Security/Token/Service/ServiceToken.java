package com.authentication.msauthentication.Security.Token.Service;

import com.authentication.msauthentication.RequestConroller.RequestUserPassword;
import com.authentication.msauthentication.service.Interface.InterfaceServiceUsers;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ServiceToken implements InterfaceServiceToken {
    public final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtDecoder jwtDecoder;
    private final InterfaceServiceUsers serviceUsers;
    private final PasswordEncoder passwordEncoder;



    @Override
    public ResponseEntity<?> jwtToken(RequestUserPassword requestUserPassword) throws PasswordException {

        if (serviceUsers.fetchUserByEmail(requestUserPassword.getEmail()).isEmpty()){
            throw new UsernameNotFoundException("Incorrect Email !");
        }

        if (!passwordEncoder.matches(requestUserPassword.getPassword(),
                serviceUsers.fetchUserByEmail(requestUserPassword.getEmail()).get().getPassword())){
            throw new PasswordException("Incorrect Password !");
        }

        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(requestUserPassword.getEmail()
                        ,requestUserPassword.getPassword()));

        Map<String,String> accessToken = new HashMap<>();
        Instant instant = Instant.now();
        String roles = authentication.getAuthorities().stream()
                .map(auth-> auth.getAuthority()).collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet =JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(9, ChronoUnit.DAYS))
                .issuer("MS-Authentication")
                .claim("scope",roles)
                .claim("firstVisit",serviceUsers.fetchUserByEmail(requestUserPassword.getEmail()).get().getFirstVisit())
                .build();

        String EncoderToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        JwtClaimsSet jwtClaimsRefresh=JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(3,ChronoUnit.DAYS))
                .issuer("MS-Authentication")
                .build();
        String EncoderRefreshToken =jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsRefresh)).getTokenValue();
        accessToken.put("AccessToken",EncoderToken);
        accessToken.put("RefreshToken",EncoderRefreshToken);
        return ResponseEntity.status(HttpStatus.OK)
                .body(accessToken);
    }

    @Override
    public ResponseEntity<?> jwtTokenAccessToken(){
        Instant instant=Instant.now();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String roles = authentication.getAuthorities().stream()
                .map(auth-> auth.getAuthority()).collect(Collectors.joining(" "));
        JwtClaimsSet accessToken=JwtClaimsSet
                .builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(9,ChronoUnit.DAYS))
                .issuer("MS-Authentication")
                .claim("scope",roles)
                .claim("firstVisit",serviceUsers.
                        fetchUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName().toString()).get().getFirstVisit())
                .build();
        String decodeToken=jwtEncoder.encode(JwtEncoderParameters.from(accessToken)).getTokenValue();
        Map<String,String> AccessToken=new HashMap<>();
        AccessToken.put("AccessToken",decodeToken);
        return  ResponseEntity.ok().body(AccessToken);
    }
}
