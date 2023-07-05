package com.moyennetransport.msmoyennetransport.Service.Class;

import com.moyennetransport.msmoyennetransport.Entity.Vehicles;
import com.moyennetransport.msmoyennetransport.Repo.RepoVehicles;
import com.moyennetransport.msmoyennetransport.Request.RequestVehicles;
import com.moyennetransport.msmoyennetransport.Service.Interface.InterfaceServiceVehicles;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class ClassService implements InterfaceServiceVehicles {
    private final RepoVehicles repoVehicles;

    @Override
    public ResponseEntity<?> Vehicles(MultipartFile file,RequestVehicles vehicles) throws IOException {

        Optional<Integer> IdVehicle = repoVehicles.getId(vehicles.getNumberPlate());
        if (IdVehicle.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Vehicles vehicles1= new Vehicles();
        vehicles1.setMarque(vehicles.getMarque());
        vehicles1.setModel(vehicles.getModel());
        vehicles1.setColor(vehicles.getColor());
        vehicles1.setFuel(vehicles.getFuel());
        vehicles1.setImage(file.getBytes());
        vehicles1.setNumberPlate(vehicles.getNumberPlate());
        vehicles1.setNumberDoors(vehicles.getNumberDoors());
        vehicles1.setYear(vehicles.getYear());
        repoVehicles.save(vehicles1);


        return ResponseEntity.ok().build();
    }

    @Override
    public List<Vehicles> fetchAll() {
        return repoVehicles.findAll();
    }

    @Override
    public Optional<Vehicles> fetchIdVehicle(Integer id) {
        return repoVehicles.findById(id);
    }

    @Override
    public ResponseEntity<?> Delete(Integer id) {
        Vehicles vehicles = repoVehicles.findById(id).get();
        repoVehicles.delete(vehicles);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updateImage(Integer id, MultipartFile file) throws IOException {
        Optional<Vehicles> optionalVehicles=repoVehicles.findById(id);
        if (optionalVehicles.isPresent()){
            Vehicles vehicle =optionalVehicles.get();
            vehicle.setImage(file.getBytes());
            repoVehicles.save(vehicle);
            return ResponseEntity.ok().body(repoVehicles.findById(id).get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> UpdateDetails(RequestVehicles vehicles) {
        Vehicles vehicle = repoVehicles.findById(vehicles.getId()).get();
        if (vehicle.getNumberPlate().equals(vehicles.getNumberPlate())) {
            vehicle.setFuel(vehicles.getFuel());
            vehicle.setColor(vehicles.getColor());
            vehicle.setModel(vehicles.getModel());
            vehicle.setYear(vehicles.getYear());
            vehicle.setMarque(vehicles.getMarque());
            vehicle.setNumberDoors(vehicles.getNumberDoors());
            vehicle.setNumberPlate(vehicles.getNumberPlate());
            repoVehicles.save(vehicle);
            return ResponseEntity.ok().body(repoVehicles.findById(vehicles.getId()).get());
        }
        else {
            Optional<Integer> id = repoVehicles.getId(vehicles.getNumberPlate());
            if (id.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }else {
                vehicle.setFuel(vehicles.getFuel());
                vehicle.setColor(vehicles.getColor());
                vehicle.setModel(vehicles.getModel());
                vehicle.setYear(vehicles.getYear());
                vehicle.setMarque(vehicles.getMarque());
                vehicle.setNumberDoors(vehicles.getNumberDoors());
                vehicle.setNumberPlate(vehicles.getNumberPlate());
                repoVehicles.save(vehicle);
                return ResponseEntity.ok().body(repoVehicles.findById(vehicles.getId()).get());
            }

        }
    }
}
