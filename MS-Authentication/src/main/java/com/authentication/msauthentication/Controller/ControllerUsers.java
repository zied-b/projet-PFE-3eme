package com.authentication.msauthentication.Controller;

import com.authentication.msauthentication.Entity.users;
import com.authentication.msauthentication.RequestConroller.RequestNewPassword;
import com.authentication.msauthentication.RequestConroller.RequestUpdateEmail;
import com.authentication.msauthentication.RequestConroller.RequestUpdateProfile;
import com.authentication.msauthentication.RequestConroller.firstVisitRequest;
import com.authentication.msauthentication.service.Interface.InterfaceServiceUsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@PreAuthorize("hasAuthority('SCOPE_Emp')")
@CrossOrigin("http://localhost:4200")
public class ControllerUsers {
    private final InterfaceServiceUsers interfaceServiceUsers ;

    public ControllerUsers(InterfaceServiceUsers interfaceServiceUsers) {
        this.interfaceServiceUsers = interfaceServiceUsers;
    }

    @GetMapping("emp/email")
    public ResponseEntity<?>fetchUserByEmail(){
        String CurrentUserEmail =interfaceServiceUsers.getCurrentUser();

        return new ResponseEntity<>(interfaceServiceUsers.fetchUserByEmail(CurrentUserEmail)
                ,HttpStatus.OK);
    }
       @PutMapping("/emp/update/name")
    public ResponseEntity<String> updateNameUser(@RequestParam String name){
        String CurrentUserEmail =interfaceServiceUsers.getCurrentUser();
        return  interfaceServiceUsers.updateNameUser(CurrentUserEmail,name);
    }
    @PutMapping("/emp/update/profile")
    public ResponseEntity<?> updateProfile(@RequestBody RequestUpdateProfile updateProfile){
        return interfaceServiceUsers.updateProfile(updateProfile);
    }
    @PutMapping("/emp/update/password")
    public ResponseEntity<String> updatePasswordUser(@RequestBody RequestNewPassword newPassword){
        String email = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        return  interfaceServiceUsers.updatePasswordUser(email,newPassword);
    }
    @PutMapping("/emp/update/image")
    public ResponseEntity<?> updatePasswordUser(@RequestParam MultipartFile file) throws IOException {
        return  interfaceServiceUsers.UpdateImageProfile(file);


    }
    @PutMapping("/emp/first-visit")
    public ResponseEntity<?> firstVisit(@RequestBody firstVisitRequest request){
        return  interfaceServiceUsers.firstVisit(request);
    }







}
