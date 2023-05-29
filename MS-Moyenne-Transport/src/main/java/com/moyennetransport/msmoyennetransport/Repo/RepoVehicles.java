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


}
