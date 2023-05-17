package com.moyennetransport.msmoyennetransport.Request;

import com.moyennetransport.msmoyennetransport.Entity.TypeVehicle;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class RequestVehicles {
    private String marque ;
    private String model;
    private String numberPlate ;
    private String typeVehicle;

}
