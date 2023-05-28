package com.authentication.msauthentication.service.Interface;


import com.authentication.msauthentication.Entity.users;
import com.authentication.msauthentication.RequestConroller.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface InterfaceServiceUsers {

    List<users> fetchAllUsers();
    ResponseEntity<String> AddUser(String Email  ) ;
    Optional<users> FetchUserById(Integer id);
    ResponseEntity<String>addRoleToUser(String Email , String Role) ;
    Optional<users>fetchUserByEmail(String Email);
    ResponseEntity<?> deleteByEmail(String Email);
    ResponseEntity<String> deleteAllUsers();
    ResponseEntity<String> updateNameUser(String Email ,String name);

    ResponseEntity<String>updateNmeByAdmin(UpdateNameUser user);
    ResponseEntity<String> updatePasswordUser(String Email , RequestNewPassword newPassword);
    ResponseEntity<String> updateEmail(RequestUserUpdateEmail updateEmail);
    String getCurrentUser();
    ResponseEntity<?> updateProfile(RequestUpdateProfile requestUpdateProfile);
    ResponseEntity<?>removeRole(String Email , String Role);
    Optional<List<users>> recherche(String recherche);

    ResponseEntity<?>UpdateImageProfile(MultipartFile file) throws IOException;


    ResponseEntity<?>firstVisit(firstVisitRequest visitRequest);


}
