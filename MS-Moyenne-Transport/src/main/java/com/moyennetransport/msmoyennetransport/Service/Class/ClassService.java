package com.moyennetransport.msmoyennetransport.Service.Class;

import com.moyennetransport.msmoyennetransport.Entity.TypeVehicle;
import com.moyennetransport.msmoyennetransport.Entity.Vehicles;
import com.moyennetransport.msmoyennetransport.Repo.RepoType;
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
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class ClassService implements InterfaceServiceVehicles {
    private final RepoVehicles repoVehicles;

    @Override
    public ResponseEntity<String> Vehicles(RequestVehicles vehicles) throws IOException {

        Optional<Integer> IdVehicle = repoVehicles.getId(vehicles.getNumberPlate());
        if (IdVehicle.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Vehicles vehicles1= new Vehicles();
        vehicles1.setMarque(vehicles.getMarque());
        vehicles1.setModel(vehicles.getModel());
        vehicles1.setColor(vehicles.getColor());
        vehicles1.setFuel(vehicles.getFuel());
        vehicles1.setImage(vehicles.getFile().getBytes());
        vehicles1.setNumberPlate(vehicles.getNumberPlate());
        vehicles1.setNumberDoors(vehicles1.getIdVehicle());
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
}
