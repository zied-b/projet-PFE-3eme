package com.moyennetransport.msmoyennetransport.Repo;

import com.moyennetransport.msmoyennetransport.Entity.TypeVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepoType extends JpaRepository<TypeVehicle,Integer> {

    Optional<TypeVehicle> findByTypeVehicle(String type);

}
