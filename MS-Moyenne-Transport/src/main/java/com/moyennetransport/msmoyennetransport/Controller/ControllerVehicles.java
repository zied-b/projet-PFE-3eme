package com.moyennetransport.msmoyennetransport.Controller;

import com.moyennetransport.msmoyennetransport.Entity.Vehicles;
import com.moyennetransport.msmoyennetransport.Request.RequestVehicles;
import com.moyennetransport.msmoyennetransport.Service.Interface.InterfaceServiceVehicles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class ControllerVehicles  {
    private final InterfaceServiceVehicles serviceVehicles;
    @PostMapping("/vehicles")
    public ResponseEntity<?> addVehicles(@RequestBody RequestVehicles vehicles) throws IOException {
        return serviceVehicles.Vehicles(vehicles);
    }
    @GetMapping("/vehicles")
    public ResponseEntity<?> fetchAllVehicles(){

            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.fetchAll());

    }

}
