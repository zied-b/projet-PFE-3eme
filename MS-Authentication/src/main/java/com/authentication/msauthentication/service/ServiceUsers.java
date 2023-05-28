package com.authentication.msauthentication.service;


import com.authentication.msauthentication.Entity.roles;
import com.authentication.msauthentication.Entity.users;
import com.authentication.msauthentication.Repo.RepoRoles;
import com.authentication.msauthentication.Repo.RepoUsers;
import com.authentication.msauthentication.RequestConroller.*;
import com.authentication.msauthentication.Security.Token.Service.InterfaceServiceToken;
import com.authentication.msauthentication.service.Interface.InterfaceServiceUsers;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ServiceUsers implements InterfaceServiceUsers {

    private final RepoRoles repoRoles;
    private final RepoUsers repoUsers;
    private final PasswordEncoder passwordEncoder;

    public ServiceUsers( RepoRoles repoRoles, RepoUsers repoUsers, PasswordEncoder passwordEncoder) {

        this.repoRoles = repoRoles;
        this.repoUsers = repoUsers;

        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<users> fetchAllUsers() {
        return repoUsers.findAll();
    }

    @Override
    public ResponseEntity<String> AddUser(String Email)  {

        Optional<users> FetchByEmail =repoUsers.findByEmail(Email);
        if (Email.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error email = Null");
        }

        else if (FetchByEmail.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This user '"+Email+"' exists ");
        }else {



            users user=new users();
            user.setPassword(passwordEncoder.encode("P@ssword1"));

            user.setEmail(Email);
          //  user.setImage(file.getBytes());
            repoUsers.save(user);
            addRoleToUser(user.getEmail(),"Emp");
            return ResponseEntity.status(HttpStatus.OK).build();
        }

    }


    @Override
    public Optional<users> FetchUserById(Integer id) {
        return repoUsers.findById(id);
    }

    @Override
    public ResponseEntity<String> addRoleToUser(String Email, String Role) {

        Optional<roles> FetchRole = repoRoles.findByRole(Role);
        Optional<users> user = repoUsers.findByEmail(Email);



        Collection<roles> rolesCollection = user.get().getRolesUser();
        rolesCollection.add(FetchRole.get());
        user.get().setRolesUser(rolesCollection);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public Optional<users> fetchUserByEmail(String Email) {
        Optional<Integer> optionalId=repoUsers.getIdByEmail(Email);
        if (optionalId.isPresent()){
            return repoUsers.findById(optionalId.get());
        }
        return Optional.empty();
    }

    @Override
    public ResponseEntity<?> deleteByEmail(String Email) {

        if (!Email.isEmpty()) {
            Optional<users> userDelete = repoUsers.findByEmail(Email);
            if (userDelete.isPresent()) {
                users user=userDelete.get();

                repoUsers.delete(user);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @Override
    public ResponseEntity<String> deleteAllUsers() {
        repoUsers.deleteAll();
       return ResponseEntity.status(HttpStatus.OK).body("Delete All users");
    }

    @Override
    public ResponseEntity<String> updateNameUser(String Email, String name) {
        Optional<users> optionalUsers = repoUsers.findByEmail(Email);
        if (optionalUsers.isPresent()) {
            users user = optionalUsers.get();
            user.setName(name);
            repoUsers.save(user);
            return new ResponseEntity<>("Update successfully !",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("The user '"+Email+"' does not exist",HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<String> updatePasswordUser(String Email, RequestNewPassword newPassword) {
        Optional<users> optionalUsers = repoUsers.findByEmail(Email);
        if (optionalUsers.isPresent()){
            users user = optionalUsers.get();
            if (passwordEncoder.matches(newPassword.getCurrentPassword(),user.getPassword())){
                user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
                repoUsers.save(user);
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(newPassword.getCurrentPassword()+newPassword.getNewPassword());
            }

        }else {
            return new ResponseEntity<>("The user '"+Email+"' does not exist",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> updateEmail(RequestUserUpdateEmail updateEmail) {
        Optional<users> optionalUsers = repoUsers.findById(updateEmail.getIdClient());
        if (optionalUsers.isPresent()){
            optionalUsers.get().setEmail(updateEmail.getEmail());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public String getCurrentUser() {
        String authentication =SecurityContextHolder.getContext().getAuthentication().getName();
        return authentication;
    }

    @Override
    public ResponseEntity<?> updateProfile(RequestUpdateProfile requestUpdateProfile) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        users userUpdate=fetchUserByEmail(email).get();
        userUpdate.setTlf(requestUpdateProfile.getTlf());
        userUpdate.setLastName(requestUpdateProfile.getLastName());
        userUpdate.setFirstName(requestUpdateProfile.getFirstName());
        repoUsers.save(userUpdate);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> removeRole(String Email, String Role) {
        Optional<users>optionalUsers=fetchUserByEmail(Email);
        Optional<roles>optionalRoles=repoRoles.findByRole(Role);
        if (optionalUsers.isPresent()&& optionalRoles.isPresent()){
            users user=optionalUsers.get();
            Collection<roles>rolesCollection=user.getRolesUser();
            rolesCollection.remove(optionalRoles.get());
            user.setRolesUser(rolesCollection);
            repoUsers.save(user);
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public Optional<List<users>> recherche(String recherche) {
        return repoUsers.recherche(recherche);
    }

    @Override
    public ResponseEntity<?> UpdateImageProfile(MultipartFile file) throws IOException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<users>optionalUsers=fetchUserByEmail(email);
        optionalUsers.get().setImage(file.getBytes());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> firstVisit(firstVisitRequest visitRequest) {
        users user = fetchUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName().toString()).get();
       user.setLastName(visitRequest.getLastName());
       user.setFirstName(visitRequest.getFirstName());
       user.setTlf(visitRequest.getTlf());
       user.setPassword(passwordEncoder.encode(visitRequest.getNewPassword()));
       user.setFirstVisit(false);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String>updateNmeByAdmin(UpdateNameUser updateNameUser){
        Optional<users> user = FetchUserById(updateNameUser.getId());
        if (user.isPresent()){
            user.get().setName(updateNameUser.getName());
            repoUsers.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}



