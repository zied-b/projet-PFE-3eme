package com.authentication.msauthentication.Controller;

import com.authentication.msauthentication.Email.EmailService;
import com.authentication.msauthentication.RequestConroller.RequestUserUpdateEmail;
import com.authentication.msauthentication.RequestConroller.UpdateNameUser;
import com.authentication.msauthentication.RequestConroller.user_roles;
import com.authentication.msauthentication.Entity.users;
import com.authentication.msauthentication.service.Interface.InterfaceServiceUsers;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasAuthority('SCOPE_AdminSys')")

public class ControllerAdminSys {
    private final InterfaceServiceUsers interfaceServiceUsers ;
    private final EmailService emailService;
    public ControllerAdminSys(InterfaceServiceUsers interfaceServiceUsers, EmailService emailService) {
        this.interfaceServiceUsers = interfaceServiceUsers;
        this.emailService = emailService;
    }

    @GetMapping("/users")
    public ResponseEntity<?>fetchAllUsers(){

        List<users> allUsers=interfaceServiceUsers.fetchAllUsers();
        if(allUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no users in the database");
        }
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }
    @PostMapping("/users")
    public ResponseEntity<String> AddNewUser(
                                             @RequestParam String Email
                                             /*@RequestParam MultipartFile file*/) throws MessagingException/* throws IOException */{
       emailService.newUserEmail(Email);

        return interfaceServiceUsers.AddUser(Email);
    }
    @GetMapping("/users/{id}")
    public Optional<users> fetchUserById(@PathVariable Integer id){
        return interfaceServiceUsers.FetchUserById(id);
    }
    @PutMapping("/users/addRoleToUser")
    public ResponseEntity<String> addNewRoleToUser(@RequestBody user_roles userRoles){
        return interfaceServiceUsers.addRoleToUser(userRoles.getEmail() ,userRoles.getRole());

    }

    @PutMapping("/users/deleteRoleToUser")
    public ResponseEntity<?> removeRole(@RequestBody user_roles userRoles){
        return interfaceServiceUsers.removeRole(userRoles.getEmail() ,userRoles.getRole());

    }
    @GetMapping("users/email")
    public ResponseEntity<?>fetchUserByEmail(@RequestParam String email){

        Optional<users> user =interfaceServiceUsers.fetchUserByEmail(email);
        if (user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<?> deleteByEmail(@RequestParam Integer id){
        return interfaceServiceUsers.deleteByEmail(id);
    }
    @DeleteMapping("/users/all")
    public ResponseEntity<String> DeleteAllUser() {
        return interfaceServiceUsers.deleteAllUsers();
    }

    @PutMapping("/users/update/email")
    public ResponseEntity<String> updateEmail(@RequestBody RequestUserUpdateEmail updateEmail){
        return interfaceServiceUsers.updateEmail(updateEmail);
    }
    @PutMapping("/users/update/name")
    public ResponseEntity<String>updateNmeByAdmin(@RequestBody UpdateNameUser updateNameUser){
      return interfaceServiceUsers.updateNmeByAdmin(updateNameUser);
    }
    @GetMapping("/recherche/{recherche}")
    public Optional<List<users>>recherche(@PathVariable String recherche){
         return interfaceServiceUsers.recherche(recherche);
    }


}
