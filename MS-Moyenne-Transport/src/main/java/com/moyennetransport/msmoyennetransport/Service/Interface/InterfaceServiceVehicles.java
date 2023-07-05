package com.moyennetransport.msmoyennetransport.Service.Interface;


import com.moyennetransport.msmoyennetransport.Entity.Vehicles;
import com.moyennetransport.msmoyennetransport.Repo.RepoVehicles;
import com.moyennetransport.msmoyennetransport.Request.RequestVehicles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface InterfaceServiceVehicles {
    public ResponseEntity<?> Vehicles(MultipartFile file,RequestVehicles vehicles) throws IOException;


    List<Vehicles> fetchAll();
    Optional<Vehicles>fetchIdVehicle(Integer id);

    ResponseEntity<?>Delete(Integer id);
    ResponseEntity<?>updateImage(Integer id ,MultipartFile file) throws IOException;
    ResponseEntity<?>UpdateDetails(RequestVehicles vehicles);




}
