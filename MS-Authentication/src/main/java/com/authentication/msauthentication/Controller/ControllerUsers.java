package com.authentication.msauthentication.Controller;

import com.authentication.msauthentication.Entity.users;
import com.authentication.msauthentication.RequestConroller.RequestNewPassword;
import com.authentication.msauthentication.RequestConroller.RequestUpdateEmail;
import com.authentication.msauthentication.service.Interface.InterfaceServiceUsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


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
    @PutMapping("/emp/update/password")
    public ResponseEntity<String> updatePasswordUser(@RequestBody RequestNewPassword newPassword){
        String CurrentUserEmail =interfaceServiceUsers.getCurrentUser();
        return  interfaceServiceUsers.updatePasswordUser(CurrentUserEmail,newPassword);
    }





}
