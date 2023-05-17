package com.authentication.msauthentication.Security.Token.Controller;

import com.authentication.msauthentication.RequestConroller.RequestUserPassword;
import com.authentication.msauthentication.Security.Token.Service.InterfaceServiceToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Validated
@RestController
@RequiredArgsConstructor
public class ControllerAccessToken implements InterfaceServiceToken {
    private final InterfaceServiceToken serviceToken;

    @Override
    @PostMapping("/Login")
    public ResponseEntity<?> jwtToken(@RequestBody @Valid RequestUserPassword requestUserPassword)
            throws UsernameNotFoundException, PasswordException {
       return serviceToken.jwtToken(requestUserPassword);
    }

    @Override
    public ResponseEntity<?> jwtTokenAccessToken() {
        return null;
    }


}
