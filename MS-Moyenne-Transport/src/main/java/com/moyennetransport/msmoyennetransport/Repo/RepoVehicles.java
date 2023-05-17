package com.moyennetransport.msmoyennetransport.Repo;

import com.moyennetransport.msmoyennetransport.Entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepoVehicles extends JpaRepository<Vehicles,Integer> {
    @Query("select  v.idVehicle from Vehicles v where v.numberPlate=:number")
    Optional<Integer>  getId(@Param("number") String number);
    Optional<Vehicles> findByNumberPlate(String NumberPlat);
    Optional<List<Vehicles>> findAllByTypeVehicle(String type);
    Optional<List<Vehicles>> findAllByModel(String Model);
    Optional<List<Vehicles>> findAllByModelAndTypeVehicle(String Model,String type);
    Optional<List<Vehicles>> findAllByMarque(String Marque);
    Optional<List<Vehicles>> findAllByMarqueAndTypeVehicle(String marque,String type);
    Optional<List<Vehicles>> findAllByMarqueAndModel(String Marque,String Model);
    Optional<List<Vehicles>> findAllByMarqueAndModelAndTypeVehicle(String Marque,String Model,String Type);


}
