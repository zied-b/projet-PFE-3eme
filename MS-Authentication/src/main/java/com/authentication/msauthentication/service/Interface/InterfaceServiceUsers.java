package com.authentication.msauthentication.service.Interface;


import com.authentication.msauthentication.Entity.users;
import com.authentication.msauthentication.RequestConroller.RequestNewPassword;
import com.authentication.msauthentication.RequestConroller.RequestUpdateEmail;
import com.authentication.msauthentication.RequestConroller.RequestUserUpdateEmail;
import com.authentication.msauthentication.RequestConroller.UpdateNameUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface InterfaceServiceUsers {

    List<users> fetchAllUsers();
    ResponseEntity<String> AddUser(String name, String Email  ) ;
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

    ResponseEntity<?>removeRole(String Email , String Role);
    Optional<List<users>> recherche(String recherche);


}
