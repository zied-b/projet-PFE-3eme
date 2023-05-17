package com.authentication.msauthentication.Email;

import com.authentication.msauthentication.Entity.users;
import com.authentication.msauthentication.Repo.RepoUsers;
import com.authentication.msauthentication.service.Interface.InterfaceServiceUsers;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.context.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final String patternPassword="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9!@#$%^&*()_+={}|;':\",./<>?`~`-]*$";
    @Value("${spring.mail.username}")
    private final String email;
    private final PasswordEncoder passwordEncoder;
    private final RepoUsers repoUsers;
    private final JavaMailSender mailSender;
    private final InterfaceServiceUsers serviceUsers;
    private final SpringTemplateEngine templateEngine;

    public ResponseEntity<?> sendNewPasswordEmail(String Email) throws MessagingException {
        Optional<users> userOptional=serviceUsers.fetchUserByEmail(Email);
        Map<String,String> MessageError= new HashMap<>();
        Map<String,String> MessageSuccessfully= new HashMap<>();
        MessageError.put("Error","Not Found Email ' "+Email+" !");
        MessageSuccessfully.put("successfully","Please check your inbox to retrieve your new password.");
        if (!userOptional.isPresent()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageError);
        }
        users userIsPresent =userOptional.get();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Your New Password");
        helper.setTo(Email);

        String password=generateNewPasswordWithPattern();
        userIsPresent.setPassword(passwordEncoder.encode(password));
        repoUsers.save(userIsPresent);
        Context context = new Context();
        context.setVariable("user", userIsPresent);
        context.setVariable("newPassword",password );

        helper.setText(templateEngine.process("new-password", context), true);

        mailSender.send(message);
        return ResponseEntity.ok().body(MessageSuccessfully);
    }
    public String generateNewPasswordWithPattern() {
        String newPassword;
        do {
            newPassword = generateNewPassword(20);
        } while (!newPassword.matches(patternPassword));
        return newPassword;
    }
    public static String generateNewPassword(int length) {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
        Random random = new Random();
        StringBuilder newPassword = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            newPassword.append(allowedChars.charAt(randomIndex));
        }

        return newPassword.toString();
    }


}

