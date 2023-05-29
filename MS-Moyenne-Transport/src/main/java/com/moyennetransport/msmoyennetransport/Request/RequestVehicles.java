package com.moyennetransport.msmoyennetransport.Request;

import com.moyennetransport.msmoyennetransport.Entity.TypeVehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor@NoArgsConstructor
public class RequestVehicles {
    private String marque ;

    private String model;

    private String numberPlate ;

    private MultipartFile file;
    private String Fuel;
    private Integer numberDoors;
    private String color;
    private Integer Year;

}
