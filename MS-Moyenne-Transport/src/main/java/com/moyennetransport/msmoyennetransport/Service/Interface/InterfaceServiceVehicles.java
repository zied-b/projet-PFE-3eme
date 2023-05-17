package com.moyennetransport.msmoyennetransport.Service.Interface;


import com.moyennetransport.msmoyennetransport.Entity.Vehicles;
import com.moyennetransport.msmoyennetransport.Repo.RepoVehicles;
import com.moyennetransport.msmoyennetransport.Request.RequestVehicles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface InterfaceServiceVehicles{
    public ResponseEntity<String> Vehicles(String numberPlate,
                                           String model,
                                           String typeVehicle,
                                           MultipartFile file,
                                           String marque) throws IOException;


    List<Vehicles> fetchAll();
    Boolean addTypeToVehicles(String type);
    ResponseEntity<?> delete(String number);
    Optional<Vehicles> findByNumberPlate(String NumberPlat);
    Optional<List<Vehicles>> findAllByTypeVehicle(String type);
    Optional<List<Vehicles>> findAllByModel(String Model);
    Optional<List<Vehicles>> findAllByModelAndTypeVehicle(String Model,String type);
    Optional<List<Vehicles>> findAllByMarque(String Marque);
    Optional<List<Vehicles>> findAllByMarqueAndTypeVehicle(String marque,String type);
    Optional<List<Vehicles>> findAllByMarqueAndModel(String Marque,String Model);
    Optional<List<Vehicles>> findAllByMarqueAndModelAndTypeVehicle(String Marque,String Model,String Type);



}
