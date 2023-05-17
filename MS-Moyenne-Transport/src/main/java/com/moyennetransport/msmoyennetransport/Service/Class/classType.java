package com.moyennetransport.msmoyennetransport.Service.Class;

import com.moyennetransport.msmoyennetransport.Entity.TypeVehicle;
import com.moyennetransport.msmoyennetransport.Repo.RepoType;
import com.moyennetransport.msmoyennetransport.Service.Interface.InterfaceServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class classType implements InterfaceServiceType {
    private final RepoType repoType;


    @Override
    public ResponseEntity<?> fetchAllType() {
        if (repoType.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Not Found !");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(repoType.findAll());
    }

    @Override
    public Optional<TypeVehicle> fetchByType(String Type) {
        return repoType.findByTypeVehicle(Type);
    }

    @Override
    public ResponseEntity<String> addType(TypeVehicle typeVehicle) {
        if (fetchByType(typeVehicle.getTypeVehicle()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("this type exist '" + typeVehicle.getTypeVehicle() + "' !");
        }
        else {
            repoType.save(typeVehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully !");
        }
    }

    @Override
    public ResponseEntity<String> deleteByType(String type) {

        if (fetchByType(type).isPresent()){
            repoType.delete(fetchByType(type).get());
            return ResponseEntity.status(HttpStatus.OK).body("Delete Type '"+type+"' !");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found  !");
        }

    }
}
