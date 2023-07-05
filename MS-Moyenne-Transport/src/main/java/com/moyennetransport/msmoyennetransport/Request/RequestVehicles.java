package com.moyennetransport.msmoyennetransport.Request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor@NoArgsConstructor
public class RequestVehicles {
    private Integer id;
    private String marque ;

    private String model;

    private String numberPlate ;


    private String Fuel;
    private Integer numberDoors;
    private String color;
    private Integer Year;

}
