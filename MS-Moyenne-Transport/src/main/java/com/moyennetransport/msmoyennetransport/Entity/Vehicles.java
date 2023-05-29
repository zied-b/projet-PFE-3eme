package com.moyennetransport.msmoyennetransport.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Vehicles{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idVehicle;

    private String marque ;

    private String model;

    private String numberPlate ;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
   private String Fuel;
   private Integer numberDoors;
   private String color;
   private Integer Year;

}
