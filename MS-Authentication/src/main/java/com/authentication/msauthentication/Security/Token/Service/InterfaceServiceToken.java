package com.authentication.msauthentication.Security.Token.Service;

import com.authentication.msauthentication.RequestConroller.RequestUserPassword;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface InterfaceServiceToken {

    public ResponseEntity<?> jwtToken( RequestUserPassword requestUserPassword) throws UsernameNotFoundException, PasswordException;
    public ResponseEntity<?> jwtTokenAccessToken();

}
