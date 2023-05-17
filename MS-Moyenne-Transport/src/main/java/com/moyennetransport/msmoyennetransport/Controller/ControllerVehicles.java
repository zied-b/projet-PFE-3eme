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
    public ResponseEntity<String> addVehicles(@RequestParam String numberPlate,
                                              @RequestParam String model,
                                              @RequestParam String typeVehicle,
                                              @RequestParam MultipartFile file,
                                              @RequestParam String marque) throws IOException {
        return serviceVehicles.Vehicles(numberPlate,model,typeVehicle,file,marque);
    }
    @GetMapping("/vehicles")
    public ResponseEntity<?> fetchAllVehicles(){
        if (serviceVehicles.fetchAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Not Found !");
        }else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.fetchAll());
        }
    }
    @DeleteMapping("/vehicles/delete")
    public ResponseEntity<?> delete(@RequestParam String number){
       return serviceVehicles.delete(number);
    }
    public ResponseEntity<?> findByNumberPlate(String NumberPlat) {

        if(serviceVehicles.findByNumberPlate(NumberPlat).isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.findByNumberPlate(NumberPlat).get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }


    public ResponseEntity<?> findAllByTypeVehicle(String type) {
        if(serviceVehicles.findAllByTypeVehicle(type).isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.findAllByTypeVehicle(type).get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }


    public ResponseEntity<?> findAllByModel(String Model) {
        if(serviceVehicles.findAllByModel(Model).isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.findAllByModel(Model).get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }


    public ResponseEntity<?> findAllByModelAndTypeVehicle(String Model, String type) {
        if(serviceVehicles.findAllByModel(Model).isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.findAllByModel(Model).get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }


    public ResponseEntity<?> findAllByMarque(String Marque) {
        if(serviceVehicles.findAllByMarque(Marque).isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.findAllByMarque(Marque).get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }


    public ResponseEntity<?> findAllByMarqueAndTypeVehicle(String marque, String type) {
        if(serviceVehicles.findAllByMarqueAndTypeVehicle(marque,type).isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.findAllByMarqueAndTypeVehicle(marque,type).get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }


    public ResponseEntity<?> findAllByMarqueAndModel(String Marque, String Model) {
        if(serviceVehicles.findAllByMarqueAndModel(Marque,Model).isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.findAllByMarqueAndModel(Marque,Model).get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }


    public ResponseEntity<?> findAllByMarqueAndModelAndTypeVehicle(String Marque, String Model, String Type) {
        if(serviceVehicles.findAllByMarqueAndModelAndTypeVehicle(Marque,Model,Type).isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.findAllByMarqueAndModelAndTypeVehicle(Marque,Model,Type).get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
}
