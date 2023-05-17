package com.moyennetransport.msmoyennetransport.Controller;

import com.moyennetransport.msmoyennetransport.Entity.TypeVehicle;
import com.moyennetransport.msmoyennetransport.Service.Interface.InterfaceServiceType;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class ControllerTypeVehicles implements InterfaceServiceType {
    private final InterfaceServiceType serviceType;
    @Override
    @GetMapping("/type")
    public ResponseEntity<?> fetchAllType() {
        return serviceType.fetchAllType();
    }
    @Override
    @PostMapping("/type")
    public ResponseEntity<String> addType(@RequestBody TypeVehicle typeVehicle) {
        return serviceType.addType(typeVehicle);
    }


    @Override
    public Optional<TypeVehicle> fetchByType(String Type) {
        return serviceType.fetchByType(Type);
    }


    @Override
    public ResponseEntity<String> deleteByType(String type) {
        return serviceType.deleteByType(type);
    }
}
