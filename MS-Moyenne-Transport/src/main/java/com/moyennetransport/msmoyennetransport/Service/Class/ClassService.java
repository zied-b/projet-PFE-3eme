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
    private final RepoType repoType;
    @Override
    public ResponseEntity<String> Vehicles(String numberPlate,
                                           String model,
                                           String typeVehicle,
                                           MultipartFile file,
                                           String marque) throws IOException {
        if (numberPlate.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Number is Empty ");
        }else if (model.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Model is Empty ");
        }else if (!repoType.findByTypeVehicle(typeVehicle).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tis Tye Not Found : "+typeVehicle);
        }else if (marque.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Marque is Empty ");
        }else if (typeVehicle.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Type vehicle is Empty ");
        }else {
            Optional<Vehicles> optionalVehicles = repoVehicles.findByNumberPlate(numberPlate);
            Optional<TypeVehicle>optionalTypeVehicle =repoType.findByTypeVehicle(typeVehicle);
            if (optionalVehicles.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("this Vehicles exist !");
            }else {

                Vehicles vehicles1 = new Vehicles();
                vehicles1.setTypeVehicle(optionalTypeVehicle.get());
                vehicles1.setMarque(marque);
                vehicles1.setModel(model);
                vehicles1.setImage(file.getBytes());
                vehicles1.setNumberPlate(numberPlate);
                repoVehicles.save(vehicles1);
               return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Created Successfully !");

            }
        }

    }
    @Override
    public Boolean addTypeToVehicles(String type){
        return  null ;
    }

    @Override
    public ResponseEntity<?> delete(String number) {
        Optional<Vehicles> vehiclesOptional= findByNumberPlate(number);
        if (vehiclesOptional.isPresent()){
            repoVehicles.delete(vehiclesOptional.get());
            return ResponseEntity.ok().build();
        }
        else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @Override
    public List<Vehicles> fetchAll() {
        return repoVehicles.findAll();
    }


    @Override
    public Optional<Vehicles> findByNumberPlate(String NumberPlat) {
        Optional<Integer> id= repoVehicles.getId(NumberPlat);
        if (id.isPresent()){
            return repoVehicles.findById(id.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Vehicles>> findAllByTypeVehicle(String type) {
        return repoVehicles.findAllByTypeVehicle(type);
    }

    @Override
    public Optional<List<Vehicles>> findAllByModel(String Model) {
        return repoVehicles.findAllByModel(Model);
    }

    @Override
    public Optional<List<Vehicles>> findAllByModelAndTypeVehicle(String Model, String type) {
        return repoVehicles.findAllByModelAndTypeVehicle(Model,type);
    }

    @Override
    public Optional<List<Vehicles>> findAllByMarque(String Marque) {
        return repoVehicles.findAllByMarque(Marque);
    }

    @Override
    public Optional<List<Vehicles>> findAllByMarqueAndTypeVehicle(String marque, String type) {
        return repoVehicles.findAllByMarqueAndTypeVehicle(marque,type);
    }

    @Override
    public Optional<List<Vehicles>> findAllByMarqueAndModel(String Marque, String Model) {
        return repoVehicles.findAllByMarqueAndModel(Marque,Model);
    }

    @Override
    public Optional<List<Vehicles>> findAllByMarqueAndModelAndTypeVehicle(String Marque, String Model, String Type) {
        return repoVehicles.findAllByMarqueAndModelAndTypeVehicle(Marque,Model,Type);
    }
}
