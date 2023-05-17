package com.authentication.msauthentication.Email;

import com.authentication.msauthentication.Repo.RepoUsers;
import jakarta.mail.MessagingException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class ControllerEmail {
    private final EmailService emailService;
    private final RepoUsers repoUsers;
    private final PasswordEncoder passwordEncoder;

    public ControllerEmail(EmailService emailService, RepoUsers repoUsers, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.repoUsers = repoUsers;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/Email")
    public ResponseEntity<?> Email(@RequestParam String Email) throws  MessagingException {


        return emailService.sendNewPasswordEmail(Email);
    }
}
