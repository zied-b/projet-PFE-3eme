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
    @Basic(optional = false)
    private String marque ;
    @Basic(optional = false)
    private String model;
    @Basic(optional = false)
    private String numberPlate ;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "associationTypeVehicle")

    private TypeVehicle typeVehicle;
   /* @Transient
    private String categorie=vehicle;*/
}
