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
    public ResponseEntity<?> addVehicles(@RequestParam("file") MultipartFile file,
                                         @RequestParam("marque") String marque,
                                         @RequestParam("model") String model,
                                         @RequestParam("numberPlate") String numberPlate,
                                         @RequestParam("fuel") String fuel,
                                         @RequestParam("numberDoors") Integer numberDoors,
                                         @RequestParam("color") String color,
                                         @RequestParam("year") Integer year) throws IOException {
        RequestVehicles vehicles = new RequestVehicles();
        vehicles.setMarque(marque);
        vehicles.setModel(model);
        vehicles.setNumberPlate(numberPlate);
        vehicles.setFuel(fuel);
        vehicles.setNumberDoors(numberDoors);
        vehicles.setColor(color);
        vehicles.setYear(year);

        return serviceVehicles.Vehicles(file,vehicles);

    }

    @GetMapping("/vehicles")
    public ResponseEntity<?> fetchAllVehicles(){

            return ResponseEntity.status(HttpStatus.OK)
                    .body(serviceVehicles.fetchAll());

    }
    @DeleteMapping("/vehicles/delete")
   public ResponseEntity<?>delete(@RequestParam Integer id){
        return serviceVehicles.Delete(id);
    }
    @PutMapping("/vehicles/image-update")
    public  ResponseEntity<?>updateImage(@RequestParam Integer id,@RequestParam MultipartFile file) throws IOException {
        return  serviceVehicles.updateImage(id,file);
    }
    @PutMapping("/vehicles/update-details")
    public ResponseEntity<?>updateDetails(@RequestBody RequestVehicles vehicles){
        return  serviceVehicles.UpdateDetails(vehicles);
    }


}
