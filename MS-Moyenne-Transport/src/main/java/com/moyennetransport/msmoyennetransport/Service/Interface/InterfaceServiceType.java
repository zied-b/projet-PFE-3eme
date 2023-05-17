package com.moyennetransport.msmoyennetransport.Service.Interface;

import com.moyennetransport.msmoyennetransport.Entity.TypeVehicle;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface InterfaceServiceType {
    public ResponseEntity<?> fetchAllType();
    public Optional<TypeVehicle> fetchByType(String Type);
    public ResponseEntity<String>addType(TypeVehicle typeVehicle);
    public ResponseEntity<String>deleteByType(String type);

}
