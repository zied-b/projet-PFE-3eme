package com.moyennetransport.msmoyennetransport.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idType;
    @Basic(optional = false)
    private String typeVehicle;
}
